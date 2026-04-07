package com.example.demo.dao.impl;

import com.example.demo.config.ConexionDB;
import com.example.demo.dao.HabitacionDAO;
import com.example.demo.model.EstadoHabitacion;
import com.example.demo.model.Habitacion;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class HabitacionDAOImpl implements HabitacionDAO {

    private final ConexionDB conexionDB;

    public HabitacionDAOImpl(ConexionDB conexionDB) {
        this.conexionDB = conexionDB;
    }

    @Override
    public List<Habitacion> findAll() {
        List<Habitacion> habitaciones = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("SELECT id, numero, tipo, precio_por_noche, imagen_url, estado FROM habitaciones");
            rs = stmt.executeQuery();
            while (rs.next()) {
                habitaciones.add(mapHabitacion(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando habitaciones", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
        return habitaciones;
    }

    @Override
    public Optional<Habitacion> findById(Long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("SELECT id, numero, tipo, precio_por_noche, imagen_url, estado FROM habitaciones WHERE id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapHabitacion(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando habitacion por id", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public Habitacion save(Habitacion habitacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO habitaciones (numero, tipo, precio_por_noche, imagen_url, estado) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, habitacion.getNumero());
            stmt.setString(2, habitacion.getTipo());
            stmt.setDouble(3, habitacion.getPrecioPorNoche());
            stmt.setString(4, habitacion.getImagenUrl());
            stmt.setString(5, habitacion.getEstado() != null ? habitacion.getEstado().name() : EstadoHabitacion.DISPONIBLE.name());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                habitacion.setId(rs.getLong(1));
            }
            return habitacion;
        } catch (SQLException e) {
            throw new RuntimeException("Error guardando habitacion", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public Habitacion update(Habitacion habitacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(
                    "UPDATE habitaciones SET numero = ?, tipo = ?, precio_por_noche = ?, imagen_url = ?, estado = ? WHERE id = ?"
            );
            stmt.setString(1, habitacion.getNumero());
            stmt.setString(2, habitacion.getTipo());
            stmt.setDouble(3, habitacion.getPrecioPorNoche());
            stmt.setString(4, habitacion.getImagenUrl());
            stmt.setString(5, habitacion.getEstado() != null ? habitacion.getEstado().name() : EstadoHabitacion.DISPONIBLE.name());
            stmt.setLong(6, habitacion.getId());
            stmt.executeUpdate();
            return habitacion;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando habitacion", e);
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
            stmt = conn.prepareStatement("DELETE FROM habitaciones WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando habitacion", e);
        } finally {
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public List<Habitacion> findDisponibles(LocalDate fechaEntrada, LocalDate fechaSalida) {
        List<Habitacion> disponibles = new ArrayList<>();
        String sql = """
                SELECT h.id, h.numero, h.tipo, h.precio_por_noche, h.imagen_url, h.estado
                FROM habitaciones h
                WHERE h.estado = 'DISPONIBLE'
                AND h.id NOT IN (
                    SELECT r.habitacion_id
                    FROM reservas r
                    WHERE r.estado NOT IN ('CANCELADA', 'FINALIZADA')
                    AND NOT (r.fecha_salida <= ? OR r.fecha_entrada >= ?)
                )
                """;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(fechaEntrada));
            stmt.setDate(2, Date.valueOf(fechaSalida));
            rs = stmt.executeQuery();
            while (rs.next()) {
                disponibles.add(mapHabitacion(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando habitaciones disponibles", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
        return disponibles;
    }

    private Habitacion mapHabitacion(ResultSet rs) throws SQLException {
        Habitacion habitacion = new Habitacion();
        habitacion.setId(rs.getLong("id"));
        habitacion.setNumero(rs.getString("numero"));
        habitacion.setTipo(rs.getString("tipo"));
        habitacion.setPrecioPorNoche(rs.getDouble("precio_por_noche"));
        habitacion.setImagenUrl(rs.getString("imagen_url"));
        String estado = rs.getString("estado");
        habitacion.setEstado(estado != null ? EstadoHabitacion.valueOf(estado) : EstadoHabitacion.DISPONIBLE);
        return habitacion;
    }
}
