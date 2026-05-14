package com.example.demo.dao.impl;

import com.example.demo.config.ConexionDB;
import com.example.demo.dao.ClienteDAO;
import com.example.demo.model.Cliente;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    private final ConexionDB conexionDB;

    public ClienteDAOImpl(ConexionDB conexionDB) {
        this.conexionDB = conexionDB;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("SELECT id, nombre, apellido, email, telefono, documento FROM clientes");
            rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(mapCliente(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando clientes", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
        return clientes;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement("SELECT id, nombre, apellido, email, telefono, documento FROM clientes WHERE id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapCliente(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando cliente por id", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public Cliente save(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO clientes (nombre, apellido, email, telefono, documento) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setString(5, cliente.getDocumentoTexto());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getLong(1));
            }
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException("Error guardando cliente", e);
        } finally {
            conexionDB.closeQuietly(rs);
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    @Override
    public Cliente update(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(
                    "UPDATE clientes SET nombre = ?, apellido = ?, email = ?, telefono = ?, documento = ? WHERE id = ?"
            );
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setString(5, cliente.getDocumentoTexto());
            stmt.setLong(6, cliente.getId());
            stmt.executeUpdate();
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando cliente", e);
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
            stmt = conn.prepareStatement("DELETE FROM clientes WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando cliente", e);
        } finally {
            conexionDB.closeQuietly(stmt);
            conexionDB.closeQuietly(conn);
        }
    }

    private Cliente mapCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellido(rs.getString("apellido"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setDocumentoTexto(rs.getString("documento"));
        return cliente;
    }
}
