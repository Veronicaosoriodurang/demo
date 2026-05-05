package com.example.demo;

import com.example.demo.dao.HabitacionDAO;
import com.example.demo.model.EstadoHabitacion;
import com.example.demo.model.Habitacion;
import com.example.demo.service.HabitacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HabitacionServiceTest {

    @Mock
    private HabitacionDAO habitacionDAO;

    @InjectMocks
    private HabitacionService habitacionService;

    @Test
    void listar_delegaEnDao() {
        Habitacion h = Habitacion.builder().id(1L).numero("101").estado(EstadoHabitacion.DISPONIBLE).build();
        when(habitacionDAO.findAll()).thenReturn(List.of(h));

        assertEquals(1, habitacionService.listar().size());
        verify(habitacionDAO).findAll();
    }

    @Test
    void buscarDisponibles_delegaEnDao() {
        LocalDate inicio = LocalDate.of(2026, 6, 1);
        LocalDate fin = LocalDate.of(2026, 6, 5);
        Habitacion h = Habitacion.builder().id(2L).numero("202").build();
        when(habitacionDAO.findDisponibles(inicio, fin)).thenReturn(List.of(h));

        assertEquals(1, habitacionService.buscarDisponibles(inicio, fin).size());
        verify(habitacionDAO).findDisponibles(inicio, fin);
    }
}
