package com.example.demo.controller;

import com.example.demo.model.Habitacion;
import com.example.demo.model.EstadoHabitacion;
import com.example.demo.service.IHabitacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
@Tag(name = "Habitaciones", description = "Operaciones sobre habitaciones")
public class HabitacionController {

    private final IHabitacionService habitacionService;

    @GetMapping
    @Operation(summary = "Listar todas las habitaciones")
    public List<Habitacion> listar() {
        return habitacionService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar todas las habitaciones")
    public ResponseEntity<Habitacion> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(habitacionService.buscarPorId(id));
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Listar todas las habitaciones")
    public List<Habitacion> porEstado(@PathVariable EstadoHabitacion estado) {
        return habitacionService.listarPorEstado(estado);
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Listar todas las habitaciones")
    public List<Habitacion> getDisponibles(@RequestParam LocalDate fechaEntrada, @RequestParam LocalDate fechaSalida) {
        return habitacionService.buscarDisponibles(fechaEntrada, fechaSalida);
    }

    @PostMapping
    @Operation(summary = "Crear nueva habitacion")
    public ResponseEntity<Habitacion> crear(@RequestBody Habitacion habitacion) {
        return ResponseEntity.ok(habitacionService.guardar(habitacion));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar habitacion")
    public ResponseEntity<Habitacion> actualizar(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        return ResponseEntity.ok(habitacionService.actualizar(id, habitacion));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar habitacion")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        habitacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}