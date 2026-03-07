package com.example.demo.service;

import com.example.demo.model.Factura;
import com.example.demo.repository.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public List<Factura> listarTodas() {
        return facturaRepository.findAll();
    }

    public Factura buscarPorId(Long id) {
        return facturaRepository.findById(id).orElseThrow();
    }

    public Factura buscarPorReserva(Long reservaId) {
        return facturaRepository.findByReservaId(reservaId);
    }

    public Factura generar(Factura factura) {
        return facturaRepository.save(factura);
    }

    public void eliminar(Long id) {
        facturaRepository.deleteById(id);
    }
}