package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.dao.ClienteDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteDAO clienteDAO;

    public List<Cliente> listarTodos() {
        return clienteDAO.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteDAO.findById(id).orElseThrow();
    }

    public Cliente guardar(Cliente cliente) {
        return clienteDAO.save(cliente);
    }

    public Cliente actualizar(Long id, Cliente cliente) {
        cliente.setId(id);
        return clienteDAO.update(cliente);
    }

    public void eliminar(Long id) {
        clienteDAO.delete(id);
    }
}