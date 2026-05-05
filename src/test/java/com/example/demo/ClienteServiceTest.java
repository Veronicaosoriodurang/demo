package com.example.demo;

import com.example.demo.dao.ClienteDAO;
import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteDAO clienteDAO;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void listar_delegaEnDao() {
        Cliente c = Cliente.builder().id(1L).nombre("Ana").apellido("Lopez").email("a@x.com").build();
        when(clienteDAO.findAll()).thenReturn(List.of(c));

        assertEquals(1, clienteService.listar().size());
        assertEquals("Ana", clienteService.listar().get(0).getNombre());
        verify(clienteDAO).findAll();
    }

    @Test
    void guardar_delegaEnDao() {
        Cliente input = Cliente.builder().nombre("Luis").apellido("Perez").email("l@x.com").build();
        Cliente saved = Cliente.builder().id(5L).nombre("Luis").apellido("Perez").email("l@x.com").build();
        when(clienteDAO.save(input)).thenReturn(saved);

        assertSame(saved, clienteService.guardar(input));
        verify(clienteDAO).save(input);
    }

    @Test
    void actualizar_delegaEnDao() {
        Cliente input = Cliente.builder().nombre("Luis").apellido("Perez").email("l@x.com").build();
        Cliente updated = Cliente.builder().id(2L).nombre("Luis").apellido("Perez").email("l@x.com").build();
        when(clienteDAO.update(any(Cliente.class))).thenReturn(updated);

        assertSame(updated, clienteService.actualizar(2L, input));
        verify(clienteDAO).update(input);
        assertEquals(2L, input.getId());
    }

    @Test
    void eliminar_delegaEnDao() {
        clienteService.eliminar(9L);
        verify(clienteDAO).delete(9L);
    }

    @Test
    void buscarPorId_delegaEnDao() {
        Cliente c = Cliente.builder().id(3L).nombre("X").build();
        when(clienteDAO.findById(3L)).thenReturn(Optional.of(c));
        assertSame(c, clienteService.buscarPorId(3L));
    }
}
