package com.example.demo.dao.impl;

import com.example.demo.config.ConexionDB;
import com.example.demo.dao.ReservaDAO;
import com.example.demo.model.Cliente;
import com.example.demo.model.Habitacion;
import com.example.demo.model.Reserva;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaDAOImpl implements ReservaDAO {

    private final ConexionDB conexionDB;

    public ReservaDAOImpl(ConexionDB conexionDB) {
        this.conexionDB = conexionDB;
    }

    @Override
    public List<Reserva> findAll() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = """
                SELECT r.id, r.fecha_entrada, r.fecha_salida, r.estado, r.total_estancia,
                       c.id AS cliente_id, c.nombre AS cliente_nombre, c.apellido AS cliente_apellido, c.email AS cliente_email,
                       h.id AS habitacion_id, h.numero AS habitacion_numero, h.tipo AS habitacion_tipo, h.precio_por_noche AS habitacion_precio, h.imagen_url AS habitacion_imagen, h.estado AS habitacion_estado
                FROM reservas r
                LEFT JOIN clientes c ON c.id = r.cliente_id
                LEFT JOIN habitaciones h ON h.id = r.habitacion_id
                """;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                reservas.add(mapReserva(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando reservas", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
        return reservas;
    }

    @Override
    public Optional<Reserva> findById(Long id) {
        String sql = """
                SELECT r.id, r.fecha_entrada, r.fecha_salida, r.estado, r.total_estancia,
                       c.id AS cliente_id, c.nombre AS cliente_nombre, c.apellido AS cliente_apellido, c.email AS cliente_email,
                       h.id AS habitacion_id, h.numero AS habitacion_numero, h.tipo AS habitacion_tipo, h.precio_por_noche AS habitacion_precio, h.imagen_url AS habitacion_imagen, h.estado AS habitacion_estado
                FROM reservas r
                LEFT JOIN clientes c ON c.id = r.cliente_id
                LEFT JOIN habitaciones h ON h.id = r.habitacion_id
                WHERE r.id = ?
                """;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapReserva(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando reserva por id", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public Reserva save(Reserva reserva) {
        String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, estado, total_estancia, cliente_id, habitacion_id) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
            stmt.setDate(2, Date.valueOf(reserva.getFechaSalida()));
            stmt.setString(3, reserva.getEstado());
            stmt.setDouble(4, reserva.getTotalEstancia() != null ? reserva.getTotalEstancia() : 0.0);
            stmt.setLong(5, reserva.getCliente().getId());
            stmt.setLong(6, reserva.getHabitacion().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                reserva.setId(rs.getLong(1));
            }
            return reserva;
        } catch (SQLException e) {
            throw new RuntimeException("Error guardando reserva", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public Reserva update(Reserva reserva) {
        String sql = "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, estado = ?, total_estancia = ?, cliente_id = ?, habitacion_id = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
            stmt.setDate(2, Date.valueOf(reserva.getFechaSalida()));
            stmt.setString(3, reserva.getEstado());
            stmt.setDouble(4, reserva.getTotalEstancia() != null ? reserva.getTotalEstancia() : 0.0);
            stmt.setLong(5, reserva.getCliente().getId());
            stmt.setLong(6, reserva.getHabitacion().getId());
            stmt.setLong(7, reserva.getId());
            stmt.executeUpdate();
            return reserva;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando reserva", e);
        } finally {
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public void delete(Long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("DELETE FROM reservas WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando reserva", e);
        } finally {
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    private Reserva mapReserva(ResultSet rs) throws SQLException {
        Reserva reserva = new Reserva();
        reserva.setId(rs.getLong("id"));
        Date fechaEntrada = rs.getDate("fecha_entrada");
        Date fechaSalida = rs.getDate("fecha_salida");
        reserva.setFechaEntrada(fechaEntrada != null ? fechaEntrada.toLocalDate() : null);
        reserva.setFechaSalida(fechaSalida != null ? fechaSalida.toLocalDate() : null);
        reserva.setEstado(rs.getString("estado"));
        reserva.setTotalEstancia(rs.getDouble("total_estancia"));

        Long clienteId = rs.getLong("cliente_id");
        if (!rs.wasNull()) {
            Cliente cliente = new Cliente();
            cliente.setId(clienteId);
            cliente.setNombre(rs.getString("cliente_nombre"));
            cliente.setApellido(rs.getString("cliente_apellido"));
            cliente.setEmail(rs.getString("cliente_email"));
            reserva.setCliente(cliente);
        }

        Long habitacionId = rs.getLong("habitacion_id");
        if (!rs.wasNull()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setId(habitacionId);
            habitacion.setNumero(rs.getString("habitacion_numero"));
            habitacion.setTipo(rs.getString("habitacion_tipo"));
            habitacion.setPrecioPorNoche(rs.getDouble("habitacion_precio"));
            habitacion.setImagenUrl(rs.getString("habitacion_imagen"));
            reserva.setHabitacion(habitacion);
        }
        return reserva;
    }
}
