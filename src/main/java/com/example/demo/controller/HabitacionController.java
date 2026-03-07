package com.example.demo.controller;

import com.example.demo.model.Habitacion;
import com.example.demo.service.HabitacionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping("/habitaciones")
    public List<Habitacion> listarHabitaciones(){
        return habitacionService.obtenerHabitaciones();
    }

    @GetMapping("/habitaciones/disponibles")
    public List<Habitacion> verDisponibles(){
        return habitacionService.habitacionesDisponibles();
    }
}