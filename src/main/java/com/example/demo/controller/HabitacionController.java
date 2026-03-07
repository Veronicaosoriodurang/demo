package com.example.demo.controller;

import com.example.demo.model.Habitacion;
import com.example.demo.model.EstadoHabitacion;
import com.example.demo.service.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;

    @GetMapping
    public List<Habitacion> listar() {
        return habitacionService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(habitacionService.buscarPorId(id));
    }

    @GetMapping("/estado/{estado}")
    public List<Habitacion> porEstado(@PathVariable EstadoHabitacion estado) {
        return habitacionService.listarPorEstado(estado);
    }

    @PostMapping
    public ResponseEntity<Habitacion> crear(@RequestBody Habitacion habitacion) {
        return ResponseEntity.ok(habitacionService.guardar(habitacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> actualizar(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        return ResponseEntity.ok(habitacionService.actualizar(id, habitacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        habitacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}