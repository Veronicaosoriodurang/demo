package com.example.demo.repository;

import com.example.demo.model.ServicioHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioHotelRepository extends JpaRepository<ServicioHotel, Long> {
}