package com.example.demo.dao;

import com.example.demo.model.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaDAO {
    List<Reserva> findAll();
    Optional<Reserva> findById(Long id);
    Reserva save(Reserva reserva);
    Reserva update(Reserva reserva);
    void delete(Long id);
}
