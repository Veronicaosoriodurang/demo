package com.example.demo.controller;

import com.example.demo.model.Habitacion;
import com.example.demo.model.EstadoHabitacion;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;
    private final ReservaRepository reservaRepository;

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

    @GetMapping("/disponibles")
    public List<Habitacion> getDisponibles(@RequestParam LocalDate fechaEntrada, @RequestParam LocalDate fechaSalida) {
        List<Long> ocupadas = reservaRepository.findHabitacionesOcupadasEntreFechas(fechaEntrada, fechaSalida);
        return habitacionService.listarTodas().stream()
                .filter(h -> h.getEstado() == EstadoHabitacion.DISPONIBLE && !ocupadas.contains(h.getId()))
                .collect(Collectors.toList());
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