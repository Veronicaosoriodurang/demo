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
public class HabitacionService {

    private final HabitacionDAO habitacionDAO;

    public List<Habitacion> listarTodas() {
        return habitacionDAO.findAll();
    }

    public Habitacion buscarPorId(Long id) {
        return habitacionDAO.findById(id).orElseThrow();
    }

    public List<Habitacion> listarPorEstado(EstadoHabitacion estado) {
        return habitacionDAO.findAll().stream()
                .filter(h -> h.getEstado() == estado)
                .collect(Collectors.toList());
    }

    public List<Habitacion> listarDisponibles(LocalDate fechaEntrada, LocalDate fechaSalida) {
        return habitacionDAO.findDisponibles(fechaEntrada, fechaSalida);
    }

    public Habitacion guardar(Habitacion habitacion) {
        return habitacionDAO.save(habitacion);
    }

    public Habitacion actualizar(Long id, Habitacion habitacion) {
        habitacion.setId(id);
        return habitacionDAO.update(habitacion);
    }

    public void eliminar(Long id) {
        habitacionDAO.delete(id);
    }
}