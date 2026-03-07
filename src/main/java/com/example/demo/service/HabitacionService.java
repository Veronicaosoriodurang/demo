package com.example.demo.service;

import com.example.demo.model.Habitacion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HabitacionService {

    private List<Habitacion> habitaciones = new ArrayList<>();

    public HabitacionService() {

        habitaciones.add(new Habitacion(101,"Simple",true,50));
        habitaciones.add(new Habitacion(102,"Doble",true,80));
        habitaciones.add(new Habitacion(103,"Suite",false,150));

    }

    public List<Habitacion> obtenerHabitaciones(){
        return habitaciones;
    }

    public List<Habitacion> habitacionesDisponibles(){

        List<Habitacion> disponibles = new ArrayList<>();

        for(Habitacion h : habitaciones){

            if(h.isDisponible()){
                disponibles.add(h);
            }

        }

        return disponibles;
    }
}