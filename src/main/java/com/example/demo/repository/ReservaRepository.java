package com.example.demo.repository;

import com.example.demo.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByClienteId(Long clienteId);

    @Query("SELECT r.habitacion.id FROM Reserva r WHERE r.estado NOT IN ('CANCELADA', 'FINALIZADA') AND NOT (r.fechaSalida <= :fechaEntrada OR r.fechaEntrada >= :fechaSalida)")
    List<Long> findHabitacionesOcupadasEntreFechas(@Param("fechaEntrada") LocalDate fechaEntrada,
                                                   @Param("fechaSalida") LocalDate fechaSalida);
}