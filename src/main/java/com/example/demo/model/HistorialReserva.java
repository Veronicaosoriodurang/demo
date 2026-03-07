package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaModificacion;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
}