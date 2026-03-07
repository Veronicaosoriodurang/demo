package com.example.demo.model;

public class Pago {

    private int id;
    private String cliente;
    private double monto;

    public Pago(int id, String cliente, double monto) {
        this.id = id;
        this.cliente = cliente;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public double getMonto() {
        return monto;
    }
}