package com.example.demo.model;

public class Habitacion {

    private int numero;
    private String tipo;
    private boolean disponible;
    private double precio;

    public Habitacion() {}

    public Habitacion(int numero, String tipo, boolean disponible, double precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.disponible = disponible;
        this.precio = precio;
    }

}