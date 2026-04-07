package com.example.demo.service;

import com.example.demo.dao.HabitacionDAO;
import com.example.demo.dao.ReservaDAO;
import com.example.demo.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckInService {

    private final ReservaDAO reservaDAO;
    private final HabitacionDAO habitacionDAO;

    public Reserva realizarCheckIn(Long reservaId) {
        Reserva reserva = reservaDAO.findById(reservaId).orElseThrow();
        Habitacion habitacion = reserva.getHabitacion();
        habitacion.setEstado(EstadoHabitacion.OCUPADA);
        habitacionDAO.update(habitacion);
        reserva.setEstado("ACTIVA");
        return reservaDAO.update(reserva);
    }
}