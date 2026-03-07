package com.example.demo.model;

import java.util.List;

public class Cliente {

    private String nombre;
    private String documento;
    private String telefono;

    private List<Reserva> reservas;

    public Cliente() {}

    public Cliente(String nombre, String documento, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public String getTelefono() { return telefono; }
}