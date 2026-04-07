package com.example.demo.service;

import com.example.demo.dao.FacturaDAO;
import com.example.demo.dao.PagoDAO;
import com.example.demo.model.Factura;
import com.example.demo.model.Pago;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoDAO pagoDAO;
    private final FacturaDAO facturaDAO;

    public List<Pago> listarTodos() {
        return pagoDAO.findAll();
    }

    public Pago buscarPorId(Long id) {
        return pagoDAO.findById(id).orElseThrow();
    }

    public List<Pago> listarPorReserva(Long reservaId) {
        return pagoDAO.findByReservaId(reservaId);
    }

    public Pago registrar(Pago pago) {
        if (pago.getReserva() == null || pago.getReserva().getId() == null) {
            throw new IllegalArgumentException("El pago debe estar asociado a una reserva valida");
        }
        pago.setFechaPago(LocalDateTime.now());
        Pago pagoGuardado = pagoDAO.save(pago);

        Factura factura = facturaDAO.findByReservaId(pagoGuardado.getReserva().getId());
        if (factura == null) {
            factura = Factura.builder().build();
            factura.setReserva(pagoGuardado.getReserva());
            factura.setTotal(pagoGuardado.getMonto());
            factura.setFechaEmision(LocalDate.now());
            facturaDAO.save(factura);
        } else {
            factura.setReserva(pagoGuardado.getReserva());
            factura.setTotal(pagoGuardado.getMonto());
            factura.setFechaEmision(LocalDate.now());
            facturaDAO.update(factura);
        }

        return pagoGuardado;
    }

    public void eliminar(Long id) {
        pagoDAO.delete(id);
    }
}