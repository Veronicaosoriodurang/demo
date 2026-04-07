package com.example.demo.controller;

import com.example.demo.model.Reserva;
import com.example.demo.service.CheckOutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
@Tag(name = "Check Out", description = "Operaciones de check-out")
public class CheckOutController {

    private final CheckOutService checkOutService;

    @PostMapping("/{reservaId}")
    @Operation(summary = "Crear nuevo check-out")
    public ResponseEntity<Reserva> checkOut(@PathVariable Long reservaId) {
        return ResponseEntity.ok(checkOutService.realizarCheckOut(reservaId));
    }
}