package com.example.demo;

import com.example.demo.dao.HabitacionDAO;
import com.example.demo.dao.ReservaDAO;
import com.example.demo.model.Habitacion;
import com.example.demo.model.Reserva;
import com.example.demo.service.ReservaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaDAO reservaDAO;

    @Mock
    private HabitacionDAO habitacionDAO;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void guardar_calculaTotalYPersiste() {
        LocalDate entrada = LocalDate.of(2026, 7, 1);
        LocalDate salida = LocalDate.of(2026, 7, 4);

        Habitacion habitacionCompleta = Habitacion.builder()
                .id(10L)
                .precioPorNoche(50.0)
                .build();

        Reserva input = Reserva.builder()
                .fechaEntrada(entrada)
                .fechaSalida(salida)
                .habitacion(Habitacion.builder().id(10L).build())
                .build();

        when(habitacionDAO.findById(10L)).thenReturn(Optional.of(habitacionCompleta));
        when(reservaDAO.save(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Reserva result = reservaService.guardar(input);

        assertEquals(150.0, result.getTotalEstancia());
        assertEquals(habitacionCompleta, result.getHabitacion());
        verify(reservaDAO).save(input);
    }

    @Test
    void cancelar_marcaEstadoCancelada() {
        Reserva existente = Reserva.builder()
                .id(7L)
                .estado("CONFIRMADA")
                .build();

        when(reservaDAO.findById(7L)).thenReturn(Optional.of(existente));
        when(reservaDAO.update(any(Reserva.class))).thenAnswer(inv -> inv.getArgument(0));

        reservaService.cancelar(7L);

        ArgumentCaptor<Reserva> captor = ArgumentCaptor.forClass(Reserva.class);
        verify(reservaDAO).update(captor.capture());
        assertEquals("CANCELADA", captor.getValue().getEstado());
    }
}
