package com.example.demo.controller;

import com.example.demo.model.Factura;
import com.example.demo.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @GetMapping
    public List<Factura> listar() {
        return facturaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.buscarPorId(id));
    }

    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<Factura> porReserva(@PathVariable Long reservaId) {
        return ResponseEntity.ok(facturaService.buscarPorReserva(reservaId));
    }

    @PostMapping
    public ResponseEntity<Factura> generar(@RequestBody Factura factura) {
        return ResponseEntity.ok(facturaService.generar(factura));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}