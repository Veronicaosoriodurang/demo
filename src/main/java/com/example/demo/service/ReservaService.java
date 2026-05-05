package com.example.demo.service;

import com.example.demo.dao.HabitacionDAO;
import com.example.demo.dao.ReservaDAO;
import com.example.demo.model.Reserva;
import com.example.demo.model.Habitacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService implements IReservaService {

    private final ReservaDAO reservaDAO;
    private final HabitacionDAO habitacionDAO;

    @Override
    public List<Reserva> listar() {
        return reservaDAO.findAll();
    }

    @Override
    public Reserva buscarPorId(Long id) {
        return reservaDAO.findById(id).orElseThrow();
    }

    @Override
    public List<Reserva> listarPorCliente(Long clienteId) {
        return reservaDAO.findAll().stream()
                .filter(r -> r.getCliente() != null && clienteId.equals(r.getCliente().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Reserva guardar(Reserva reserva) {
        if (reserva.getHabitacion() == null || reserva.getHabitacion().getId() == null) {
            throw new IllegalArgumentException("La reserva debe incluir una habitacion valida");
        }
        if (reserva.getFechaEntrada() == null || reserva.getFechaSalida() == null) {
            throw new IllegalArgumentException("La reserva debe incluir fecha de entrada y salida");
        }

        Habitacion habitacion = habitacionDAO.findById(reserva.getHabitacion().getId()).orElseThrow();
        long dias = ChronoUnit.DAYS.between(reserva.getFechaEntrada(), reserva.getFechaSalida());
        if (dias <= 0) {
            throw new IllegalArgumentException("La fecha de salida debe ser mayor que la fecha de entrada");
        }

        double totalEstancia = dias * habitacion.getPrecioPorNoche();
        reserva.setHabitacion(habitacion);
        reserva.setTotalEstancia(totalEstancia);
        return reservaDAO.save(reserva);
    }

    @Override
    public void cancelar(Long id) {
        Reserva reserva = buscarPorId(id);
        reserva.setEstado("CANCELADA");
        reservaDAO.update(reserva);
    }

    @Override
    public void eliminar(Long id) {
        reservaDAO.delete(id);
    }
}