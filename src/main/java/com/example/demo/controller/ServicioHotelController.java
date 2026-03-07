package com.example.demo.controller;

import com.example.demo.model.ServicioHotel;
import com.example.demo.service.ServicioHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@RequiredArgsConstructor
public class ServicioHotelController {

    private final ServicioHotelService servicioHotelService;

    @GetMapping
    public List<ServicioHotel> listar() {
        return servicioHotelService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioHotel> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(servicioHotelService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ServicioHotel> crear(@RequestBody ServicioHotel servicio) {
        return ResponseEntity.ok(servicioHotelService.guardar(servicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioHotel> actualizar(@PathVariable Long id, @RequestBody ServicioHotel servicio) {
        return ResponseEntity.ok(servicioHotelService.actualizar(id, servicio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioHotelService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}