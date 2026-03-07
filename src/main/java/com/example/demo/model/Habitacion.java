package com.example.demo.model;

public class Habitacion {

    private int numero;
    private String tipo;
    private boolean disponible;
    private double precio;

    public Habitacion(int numero, String tipo, boolean disponible, double precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.disponible = disponible;
        this.precio = precio;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}