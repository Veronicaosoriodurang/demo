package com.example.demo.dao.impl;

import com.example.demo.config.ConexionDB;
import com.example.demo.dao.FacturaDAO;
import com.example.demo.model.Factura;
import com.example.demo.model.Reserva;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FacturaDAOImpl implements FacturaDAO {

    private final ConexionDB conexionDB;

    public FacturaDAOImpl(ConexionDB conexionDB) {
        this.conexionDB = conexionDB;
    }

    @Override
    public List<Factura> findAll() {
        List<Factura> facturas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("SELECT id, fecha_emision, total, reserva_id FROM facturas");
            rs = stmt.executeQuery();
            while (rs.next()) {
                facturas.add(mapFactura(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando facturas", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
        return facturas;
    }

    @Override
    public Optional<Factura> findById(Long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("SELECT id, fecha_emision, total, reserva_id FROM facturas WHERE id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapFactura(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando factura por id", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public Factura findByReservaId(Long reservaId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("SELECT id, fecha_emision, total, reserva_id FROM facturas WHERE reserva_id = ?");
            stmt.setLong(1, reservaId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return mapFactura(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando factura por reserva", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public Factura save(Factura factura) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO facturas (fecha_emision, total, reserva_id) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setDate(1, Date.valueOf(factura.getFechaEmision()));
            stmt.setDouble(2, factura.getTotal());
            stmt.setLong(3, factura.getReserva().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                factura.setId(rs.getLong(1));
            }
            return factura;
        } catch (SQLException e) {
            throw new RuntimeException("Error guardando factura", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public Factura update(Factura factura) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("UPDATE facturas SET fecha_emision = ?, total = ?, reserva_id = ? WHERE id = ?");
            stmt.setDate(1, Date.valueOf(factura.getFechaEmision()));
            stmt.setDouble(2, factura.getTotal());
            stmt.setLong(3, factura.getReserva().getId());
            stmt.setLong(4, factura.getId());
            stmt.executeUpdate();
            return factura;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando factura", e);
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
            stmt = conn.prepareStatement("DELETE FROM facturas WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando factura", e);
        } finally {
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    private Factura mapFactura(ResultSet rs) throws SQLException {
        Factura factura = new Factura();
        factura.setId(rs.getLong("id"));
        Date fecha = rs.getDate("fecha_emision");
        factura.setFechaEmision(fecha != null ? fecha.toLocalDate() : null);
        factura.setTotal(rs.getDouble("total"));
        Reserva reserva = new Reserva();
        reserva.setId(rs.getLong("reserva_id"));
        factura.setReserva(reserva);
        return factura;
    }
}
