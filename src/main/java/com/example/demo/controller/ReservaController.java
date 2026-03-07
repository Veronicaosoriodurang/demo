package com.example.demo.controller;

import com.example.demo.model.Reserva;
import com.example.demo.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Reserva> porCliente(@PathVariable Long clienteId) {
        return reservaService.listarPorCliente(clienteId);
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.guardar(reserva));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        reservaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}