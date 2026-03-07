package com.example.demo.model;

public class Reserva {

    private int id;
    private String cliente;
    private int numeroHabitacion;
    private String fechaEntrada;
    private String fechaSalida;

    public Reserva(int id, String cliente, int numeroHabitacion, String fechaEntrada, String fechaSalida) {
        this.id = id;
        this.cliente = cliente;
        this.numeroHabitacion = numeroHabitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }
}