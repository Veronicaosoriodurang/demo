package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaEmision;
    private Double total;

    @OneToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private List<FacturaDetalle> detalles;
}