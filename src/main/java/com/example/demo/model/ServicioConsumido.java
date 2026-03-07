package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "servicios_consumidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicioConsumido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;
    private LocalDateTime fechaConsumo;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private ServicioHotel servicio;
}