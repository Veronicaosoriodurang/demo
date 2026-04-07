package com.example.demo.dao.impl;

import com.example.demo.config.ConexionDB;
import com.example.demo.dao.PagoDAO;
import com.example.demo.model.MetodoPago;
import com.example.demo.model.Pago;
import com.example.demo.model.Reserva;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PagoDAOImpl implements PagoDAO {

    private final ConexionDB conexionDB;

    public PagoDAOImpl(ConexionDB conexionDB) {
        this.conexionDB = conexionDB;
    }

    @Override
    public List<Pago> findAll() {
        List<Pago> pagos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("SELECT id, monto, fecha_pago, metodo_pago, reserva_id FROM pagos");
            rs = stmt.executeQuery();
            while (rs.next()) {
                pagos.add(mapPago(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando pagos", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
        return pagos;
    }

    @Override
    public Optional<Pago> findById(Long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("SELECT id, monto, fecha_pago, metodo_pago, reserva_id FROM pagos WHERE id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapPago(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando pago por id", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public List<Pago> findByReservaId(Long reservaId) {
        List<Pago> pagos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("SELECT id, monto, fecha_pago, metodo_pago, reserva_id FROM pagos WHERE reserva_id = ?");
            stmt.setLong(1, reservaId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pagos.add(mapPago(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando pagos por reserva", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
        return pagos;
    }

    @Override
    public Pago save(Pago pago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO pagos (monto, fecha_pago, metodo_pago, reserva_id) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setDouble(1, pago.getMonto());
            stmt.setTimestamp(2, Timestamp.valueOf(pago.getFechaPago()));
            stmt.setString(3, pago.getMetodoPago().name());
            stmt.setLong(4, pago.getReserva().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                pago.setId(rs.getLong(1));
            }
            return pago;
        } catch (SQLException e) {
            throw new RuntimeException("Error guardando pago", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public Pago update(Pago pago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(
                    "UPDATE pagos SET monto = ?, fecha_pago = ?, metodo_pago = ?, reserva_id = ? WHERE id = ?"
            );
            stmt.setDouble(1, pago.getMonto());
            stmt.setTimestamp(2, Timestamp.valueOf(pago.getFechaPago()));
            stmt.setString(3, pago.getMetodoPago().name());
            stmt.setLong(4, pago.getReserva().getId());
            stmt.setLong(5, pago.getId());
            stmt.executeUpdate();
            return pago;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando pago", e);
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
            stmt = conn.prepareStatement("DELETE FROM pagos WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando pago", e);
        } finally {
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    private Pago mapPago(ResultSet rs) throws SQLException {
        Pago pago = new Pago();
        pago.setId(rs.getLong("id"));
        pago.setMonto(rs.getDouble("monto"));
        Timestamp ts = rs.getTimestamp("fecha_pago");
        pago.setFechaPago(ts != null ? ts.toLocalDateTime() : null);
        String metodo = rs.getString("metodo_pago");
        pago.setMetodoPago(metodo != null ? MetodoPago.valueOf(metodo) : MetodoPago.EFECTIVO);

        Reserva reserva = new Reserva();
        reserva.setId(rs.getLong("reserva_id"));
        pago.setReserva(reserva);
        return pago;
    }
}
