package com.example.demo.service;

import com.example.demo.dao.FacturaDAO;
import com.example.demo.model.Factura;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaDAO facturaDAO;

    public List<Factura> listarTodas() {
        return facturaDAO.findAll();
    }

    public Factura buscarPorId(Long id) {
        return facturaDAO.findById(id).orElseThrow();
    }

    public Factura buscarPorReserva(Long reservaId) {
        return facturaDAO.findByReservaId(reservaId);
    }

    public Factura generar(Factura factura) {
        Factura existente = facturaDAO.findByReservaId(factura.getReserva().getId());
        if (existente != null) {
            existente.setFechaEmision(factura.getFechaEmision());
            existente.setTotal(factura.getTotal());
            existente.setReserva(factura.getReserva());
            return facturaDAO.update(existente);
        }
        return facturaDAO.save(factura);
    }

    public void eliminar(Long id) {
        facturaDAO.delete(id);
    }
}