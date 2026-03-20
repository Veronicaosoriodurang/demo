package com.example.demo.service;

import com.example.demo.model.Reserva;
import com.example.demo.model.Habitacion;
import com.example.demo.repository.HabitacionRepository;
import com.example.demo.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final HabitacionRepository habitacionRepository;

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Reserva buscarPorId(Long id) {
        return reservaRepository.findById(id).orElseThrow();
    }

    public List<Reserva> listarPorCliente(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId);
    }

    public Reserva guardar(Reserva reserva) {
        if (reserva.getHabitacion() == null || reserva.getHabitacion().getId() == null) {
            throw new IllegalArgumentException("La reserva debe incluir una habitacion valida");
        }
        if (reserva.getFechaEntrada() == null || reserva.getFechaSalida() == null) {
            throw new IllegalArgumentException("La reserva debe incluir fecha de entrada y salida");
        }

        Habitacion habitacion = habitacionRepository.findById(reserva.getHabitacion().getId()).orElseThrow();
        long dias = ChronoUnit.DAYS.between(reserva.getFechaEntrada(), reserva.getFechaSalida());
        if (dias <= 0) {
            throw new IllegalArgumentException("La fecha de salida debe ser mayor que la fecha de entrada");
        }

        double totalEstancia = dias * habitacion.getPrecioPorNoche();
        reserva.setHabitacion(habitacion);
        reserva.setTotalEstancia(totalEstancia);
        return reservaRepository.save(reserva);
    }

    public void cancelar(Long id) {
        Reserva reserva = buscarPorId(id);
        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);
    }

    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }
}