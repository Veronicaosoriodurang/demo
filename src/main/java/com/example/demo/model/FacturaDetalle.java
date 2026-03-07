package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "factura_detalles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}