package com.example.demo.model;

public class Pago {

    private int id;
    private double monto;

    private MetodoPago metodoPago;

    public Pago() {}

    public Pago(int id, double monto, MetodoPago metodoPago) {
        this.id = id;
        this.monto = monto;
        this.metodoPago = metodoPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
}