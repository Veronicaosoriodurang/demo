package com.example.demo.controller;

import com.example.demo.model.Reserva;
import com.example.demo.service.CheckInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkin")
@RequiredArgsConstructor
public class CheckInController {

    private final CheckInService checkInService;

    @PostMapping("/{reservaId}")
    public ResponseEntity<Reserva> checkIn(@PathVariable Long reservaId) {
        return ResponseEntity.ok(checkInService.realizarCheckIn(reservaId));
    }
}