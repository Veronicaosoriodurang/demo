package com.example.demo.service;

import com.example.demo.model.Pago;
import com.example.demo.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;

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
        return pagoRepository.save(pago);
    }

    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }
}