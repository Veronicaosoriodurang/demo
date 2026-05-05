package com.example.demo.service;

import com.example.demo.model.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> listar();

    Cliente buscarPorId(Long id);

    Cliente guardar(Cliente cliente);

    Cliente actualizar(Long id, Cliente cliente);

    void eliminar(Long id);
}
