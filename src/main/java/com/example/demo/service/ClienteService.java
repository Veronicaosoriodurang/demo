package com.example.demo.service;

import com.example.demo.model.Cliente;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public void registrarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public List<Cliente> obtenerClientes(){
        return clientes;
    }
}