package com.example.demo.service;

import com.example.demo.model.ServicioHotel;
import com.example.demo.repository.ServicioHotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioHotelService {

    private final ServicioHotelRepository servicioHotelRepository;

    public List<ServicioHotel> listarTodos() {
        return servicioHotelRepository.findAll();
    }

    public ServicioHotel buscarPorId(Long id) {
        return servicioHotelRepository.findById(id).orElseThrow();
    }

    public ServicioHotel guardar(ServicioHotel servicio) {
        return servicioHotelRepository.save(servicio);
    }

    public ServicioHotel actualizar(Long id, ServicioHotel servicio) {
        servicio.setId(id);
        return servicioHotelRepository.save(servicio);
    }

    public void eliminar(Long id) {
        servicioHotelRepository.deleteById(id);
    }
}