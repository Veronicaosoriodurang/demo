package com.example.demo.repository;

import com.example.demo.model.Habitacion;
import com.example.demo.model.EstadoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    List<Habitacion> findByEstado(EstadoHabitacion estado);
}