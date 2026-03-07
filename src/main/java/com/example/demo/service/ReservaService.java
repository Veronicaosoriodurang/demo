package com.example.demo.service;

import com.example.demo.model.Reserva;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    private List<Reserva> reservas = new ArrayList<>();

    public void crearReserva(Reserva reserva){
        reservas.add(reserva);
    }

    public List<Reserva> obtenerReservas(){
        return reservas;
    }
}