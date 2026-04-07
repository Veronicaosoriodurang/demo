package com.example.demo.controller;

import com.example.demo.model.Reserva;
import com.example.demo.service.CheckInService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkin")
@RequiredArgsConstructor
@Tag(name = "Check In", description = "Operaciones de check-in")
public class CheckInController {

    private final CheckInService checkInService;

    @PostMapping("/{reservaId}")
    @Operation(summary = "Crear nuevo check-in")
    public ResponseEntity<Reserva> checkIn(@PathVariable Long reservaId) {
        return ResponseEntity.ok(checkInService.realizarCheckIn(reservaId));
    }
}