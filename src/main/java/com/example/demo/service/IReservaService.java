package com.example.demo.service;

import com.example.demo.model.Reserva;

import java.util.List;

public interface IReservaService {

    List<Reserva> listar();

    Reserva buscarPorId(Long id);

    Reserva guardar(Reserva reserva);

    void cancelar(Long id);

    void eliminar(Long id);

    List<Reserva> listarPorCliente(Long clienteId);
}
