package com.example.demo.service;

import com.example.demo.model.Habitacion;
import com.example.demo.model.EstadoHabitacion;
import com.example.demo.dao.HabitacionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitacionService implements IHabitacionService {

    private final HabitacionDAO habitacionDAO;

    @Override
    public List<Habitacion> listar() {
        return habitacionDAO.findAll();
    }

    @Override
    public Habitacion buscarPorId(Long id) {
        return habitacionDAO.findById(id).orElseThrow();
    }

    @Override
    public List<Habitacion> listarPorEstado(EstadoHabitacion estado) {
        return habitacionDAO.findAll().stream()
                .filter(h -> h.getEstado() == estado)
                .collect(Collectors.toList());
    }

    @Override
    public List<Habitacion> buscarDisponibles(LocalDate fechaEntrada, LocalDate fechaSalida) {
        return habitacionDAO.findDisponibles(fechaEntrada, fechaSalida);
    }

    @Override
    public Habitacion guardar(Habitacion habitacion) {
        return habitacionDAO.save(habitacion);
    }

    @Override
    public Habitacion actualizar(Long id, Habitacion habitacion) {
        habitacion.setId(id);
        return habitacionDAO.update(habitacion);
    }

    @Override
    public void eliminar(Long id) {
        habitacionDAO.delete(id);
    }
}