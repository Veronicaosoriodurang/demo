package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/clientes")
    public String registrarCliente(@RequestBody Cliente cliente){
        clienteService.registrarCliente(cliente);
        return "Cliente registrado correctamente";
    }

    @GetMapping("/clientes")
    public List<Cliente> verClientes(){
        return clienteService.obtenerClientes();
    }
}