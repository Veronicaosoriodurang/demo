package com.example.demo.model;

public class ServicioConsumido {

    private int id;
    private String nombreServicio;
    private int cantidad;
    private double precio;

    public ServicioConsumido() {
    }

    public ServicioConsumido(int id, String nombreServicio, int cantidad, double precio) {
        this.id = id;
        this.nombreServicio = nombreServicio;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}