package com.example.demo.dao;

import com.example.demo.model.Habitacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitacionDAO {
    List<Habitacion> findAll();
    Optional<Habitacion> findById(Long id);
    Habitacion save(Habitacion habitacion);
    Habitacion update(Habitacion habitacion);
    void delete(Long id);
    List<Habitacion> findDisponibles(LocalDate fechaEntrada, LocalDate fechaSalida);
}
