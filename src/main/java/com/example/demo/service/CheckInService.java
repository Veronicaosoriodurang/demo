package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckInService {

    private final ReservaRepository reservaRepository;
    private final HabitacionRepository habitacionRepository;

    public Reserva realizarCheckIn(Long reservaId) {
        Reserva reserva = reservaRepository.findById(reservaId).orElseThrow();
        Habitacion habitacion = reserva.getHabitacion();
        habitacion.setEstado(EstadoHabitacion.OCUPADA);
        habitacionRepository.save(habitacion);
        reserva.setEstado("ACTIVA");
        return reservaRepository.save(reserva);
    }
}