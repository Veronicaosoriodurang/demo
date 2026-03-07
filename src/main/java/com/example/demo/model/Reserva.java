package com.example.demo.model;

public class Reserva {

    private int id;

    private Cliente cliente;
    private Habitacion habitacion;
    private Pago pago;

    private String fechaEntrada;
    private String fechaSalida;

    public Reserva() {}

    public Reserva(int id, Cliente cliente, Habitacion habitacion, String fechaEntrada, String fechaSalida) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public Cliente getCliente() { return cliente; }
    public Habitacion getHabitacion() { return habitacion; }
    public Pago getPago() { return pago; }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }
    public void setPago(Pago pago) { this.pago = pago; }
}