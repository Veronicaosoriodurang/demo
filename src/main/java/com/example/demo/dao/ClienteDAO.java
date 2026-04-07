package com.example.demo.dao;

import com.example.demo.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);
    Cliente update(Cliente cliente);
    void delete(Long id);
}
