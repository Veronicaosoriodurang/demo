package com.example.demo.controller;

import com.example.demo.model.Factura;
import com.example.demo.service.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
@Tag(name = "Facturas", description = "Operaciones sobre facturas")
public class FacturaController {

    private final FacturaService facturaService;

    @GetMapping
    @Operation(summary = "Listar todas las facturas")
    public List<Factura> listar() {
        return facturaService.listarTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar todas las facturas")
    public ResponseEntity<Factura> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.buscarPorId(id));
    }

    @GetMapping("/reserva/{reservaId}")
    @Operation(summary = "Listar todas las facturas")
    public ResponseEntity<Factura> porReserva(@PathVariable Long reservaId) {
        return ResponseEntity.ok(facturaService.buscarPorReserva(reservaId));
    }

    @PostMapping
    @Operation(summary = "Crear nueva factura")
    public ResponseEntity<Factura> generar(@RequestBody Factura factura) {
        return ResponseEntity.ok(facturaService.generar(factura));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar factura")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}