package com.example.demo.controller;

import com.example.demo.model.Pago;
import com.example.demo.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
@Tag(name = "Pagos", description = "Operaciones sobre pagos")
public class PagoController {

    private final PagoService pagoService;

    @GetMapping
    @Operation(summary = "Listar todos los pagos")
    public List<Pago> listar() {
        return pagoService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar todos los pagos")
    public ResponseEntity<Pago> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @GetMapping("/reserva/{reservaId}")
    @Operation(summary = "Listar todos los pagos")
    public List<Pago> porReserva(@PathVariable Long reservaId) {
        return pagoService.listarPorReserva(reservaId);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo pago")
    public ResponseEntity<Pago> registrar(@RequestBody Pago pago) {
        return ResponseEntity.ok(pagoService.registrar(pago));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pago")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}