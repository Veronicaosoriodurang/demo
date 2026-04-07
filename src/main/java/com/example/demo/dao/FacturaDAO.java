package com.example.demo.dao;

import com.example.demo.model.Factura;

import java.util.List;
import java.util.Optional;

public interface FacturaDAO {
    List<Factura> findAll();
    Optional<Factura> findById(Long id);
    Factura findByReservaId(Long reservaId);
    Factura save(Factura factura);
    Factura update(Factura factura);
    void delete(Long id);
}
