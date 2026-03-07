package com.example.demo.service;

import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    public Empleado buscarPorId(Long id) {
        return empleadoRepository.findById(id).orElseThrow();
    }

    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado actualizar(Long id, Empleado empleado) {
        empleado.setId(id);
        return empleadoRepository.save(empleado);
    }

    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }
}