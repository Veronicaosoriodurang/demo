package com.example.demo.service;

import com.example.demo.model.Pago;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagoService {

    private List<Pago> pagos = new ArrayList<>();

    public Pago registrarPago(Pago pago){
        pagos.add(pago);
        return pago;
    }

    public List<Pago> obtenerPagos(){
        return pagos;
    }
}