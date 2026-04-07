package com.example.demo.dao;

import com.example.demo.model.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoDAO {
    List<Pago> findAll();
    Optional<Pago> findById(Long id);
    List<Pago> findByReservaId(Long reservaId);
    Pago save(Pago pago);
    Pago update(Pago pago);
    void delete(Long id);
}
