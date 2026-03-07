package com.example.demo.service;

import com.example.demo.model.Habitacion;
import com.example.demo.model.EstadoHabitacion;
import com.example.demo.repository.HabitacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;

    public List<Habitacion> listarTodas() {
        return habitacionRepository.findAll();
    }

    public Habitacion buscarPorId(Long id) {
        return habitacionRepository.findById(id).orElseThrow();
    }

    public List<Habitacion> listarPorEstado(EstadoHabitacion estado) {
        return habitacionRepository.findByEstado(estado);
    }

    public Habitacion guardar(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public Habitacion actualizar(Long id, Habitacion habitacion) {
        habitacion.setId(id);
        return habitacionRepository.save(habitacion);
    }

    public void eliminar(Long id) {
        habitacionRepository.deleteById(id);
    }
}