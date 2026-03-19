package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "habitaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String tipo;
    private Double precioPorNoche;
    private String imagenUrl;

    @ManyToOne
    @JoinColumn(name = "piso_id")
    private Piso piso;

    @Enumerated(EnumType.STRING)
    private EstadoHabitacion estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disponibilidad_id")
    private Disponibilidad disponibilidad;
}