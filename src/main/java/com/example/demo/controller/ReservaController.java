package com.example.demo.controller;

import com.example.demo.model.Reserva;
import com.example.demo.service.ReservaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/reservas")
    public String crearReserva(@RequestBody Reserva reserva){
        reservaService.crearReserva(reserva);
        return "Reserva creada correctamente";
    }

    @GetMapping("/reservas")
    public List<Reserva> verReservas(){
        return reservaService.obtenerReservas();
    }
}