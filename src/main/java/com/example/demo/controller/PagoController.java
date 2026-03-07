package com.example.demo.controller;

import com.example.demo.model.Pago;
import com.example.demo.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @GetMapping
    public List<Pago> listar() {
        return pagoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @GetMapping("/reserva/{reservaId}")
    public List<Pago> porReserva(@PathVariable Long reservaId) {
        return pagoService.listarPorReserva(reservaId);
    }

    @PostMapping
    public ResponseEntity<Pago> registrar(@RequestBody Pago pago) {
        return ResponseEntity.ok(pagoService.registrar(pago));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}