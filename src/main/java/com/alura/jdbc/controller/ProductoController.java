package com.alura.jdbc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

public class ProductoController {

	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
		final Connection con = new ConnectionFactory().recuperaConexion();
		try (con) {
			final PreparedStatement decla = con
					.prepareStatement("UPDATE PRODUCTO SET NOMBRE = ?, DESCRIPCION = ?, CANTIDAD = ? WHERE ID = ?;");
			try (decla) {
				decla.setString(1, nombre);
				decla.setString(2, descripcion);
				decla.setInt(3, cantidad);
				decla.setInt(4, id);
				decla.execute();
				int nModificados = decla.getUpdateCount();
				return nModificados;
			}
		}
	}

	public int eliminar(Integer id) throws SQLException {
		final Connection con = new ConnectionFactory().recuperaConexion();
		try (con) {
			final PreparedStatement decla = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID= ?");
			try (decla) {
				decla.setInt(1, id);
				decla.execute();

				int nModificados = decla.getUpdateCount();
				System.out.println(
						"Se ha eliminado " + nModificados + " items");
				return nModificados;
			}
		}
	}

	public List<Map<String, String>> listar() throws SQLException {
		final Connection con = new ConnectionFactory().recuperaConexion();
		// try-with-resources - Java 7+
		try (con) {
			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
			try (statement) {
				statement.execute();
				final ResultSet resu = statement.getResultSet();
				try (resu) {
					List<Map<String, String>> lista = new ArrayList<>();
					while (resu.next()) {
						Map<String, String> fila = new HashMap<>();
						fila.put("ID", String.valueOf(resu.getInt("ID")));
						fila.put("NOMBRE", resu.getString("NOMBRE"));
						fila.put("DESCRIPCION", resu.getString("DESCRIPCION"));
						fila.put("CANTIDAD", String.valueOf(resu.getInt("CANTIDAD")));
						// nombre de columna o numero

						lista.add(fila);
					}
					return lista;
				}
			}
		}
	}

	public void guardar(Producto producto) throws SQLException {

		final Connection con = new ConnectionFactory().recuperaConexion();
		try (con) {
			con.setAutoCommit(false);
			final PreparedStatement decla = con.prepareStatement(
					"INSERT INTO PRODUCTO(NOMBRE, DESCRIPCION, CANTIDAD) VALUES( ?, ?, ? )",
					Statement.RETURN_GENERATED_KEYS);

			try (decla) {
				instruccion(producto, decla);
				con.commit();
				System.out.println("commit");
			} catch (Exception e) {
				e.printStackTrace();
				con.rollback();
				System.out.println("Rollback");
			}
		}
	}

	private void instruccion(Producto producto, PreparedStatement decla)
			throws SQLException {
		decla.setString(1, producto.getNombre());
		decla.setString(2, producto.getDescripcion());
		decla.setInt(3, producto.getCantidad());
		decla.execute();
		ResultSet resultado = decla.getGeneratedKeys();

		while (resultado.next()) {
			producto.setId(resultado.getInt(1));
			System.out.println(String.format("fue insertado el producto %s", producto));
		}
	}
}