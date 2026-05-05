package com.example.demo.service;

import com.example.demo.model.EstadoHabitacion;
import com.example.demo.model.Habitacion;

import java.time.LocalDate;
import java.util.List;

public interface IHabitacionService {

    List<Habitacion> listar();

    Habitacion buscarPorId(Long id);

    Habitacion guardar(Habitacion habitacion);

    Habitacion actualizar(Long id, Habitacion habitacion);

    void eliminar(Long id);

    List<Habitacion> buscarDisponibles(LocalDate fechaEntrada, LocalDate fechaSalida);

    List<Habitacion> listarPorEstado(EstadoHabitacion estado);
}
