package com.example.demo.controller;

import com.example.demo.model.Reserva;
import com.example.demo.service.CheckOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckOutController {

    private final CheckOutService checkOutService;

    @PostMapping("/{reservaId}")
    public ResponseEntity<Reserva> checkOut(@PathVariable Long reservaId) {
        return ResponseEntity.ok(checkOutService.realizarCheckOut(reservaId));
    }
}