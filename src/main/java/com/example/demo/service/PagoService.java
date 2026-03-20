package com.example.demo.service;

import com.example.demo.model.Factura;
import com.example.demo.model.Pago;
import com.example.demo.repository.FacturaRepository;
import com.example.demo.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final FacturaRepository facturaRepository;

    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }

    public Pago buscarPorId(Long id) {
        return pagoRepository.findById(id).orElseThrow();
    }

    public List<Pago> listarPorReserva(Long reservaId) {
        return pagoRepository.findByReservaId(reservaId);
    }

    public Pago registrar(Pago pago) {
        if (pago.getReserva() == null || pago.getReserva().getId() == null) {
            throw new IllegalArgumentException("El pago debe estar asociado a una reserva valida");
        }
        pago.setFechaPago(LocalDateTime.now());
        Pago pagoGuardado = pagoRepository.save(pago);

        Factura factura = facturaRepository.findByReservaId(pagoGuardado.getReserva().getId());
        if (factura == null) {
            factura = Factura.builder().build();
        }
        factura.setReserva(pagoGuardado.getReserva());
        factura.setTotal(pagoGuardado.getMonto());
        factura.setFechaEmision(LocalDate.now());
        facturaRepository.save(factura);

        return pagoGuardado;
    }

    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }
}