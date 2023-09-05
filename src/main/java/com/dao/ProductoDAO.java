package com.dao;

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

public class ProductoDAO {
	final private Connection con;

	public ProductoDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Producto producto) {
		try  {
			PreparedStatement decla = con.prepareStatement(
					"INSERT INTO PRODUCTO(NOMBRE, DESCRIPCION, CANTIDAD) VALUES( ?, ?, ? )",
					Statement.RETURN_GENERATED_KEYS);

			try (decla) {
				instruccion(producto, decla);
			}

		}catch (SQLException e) {
			throw new RuntimeException(e);
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

	public List<Producto> listar() {
		final Connection con = new ConnectionFactory().recuperaConexion();
		// try-with-resources - Java 7+
		try (con) {
			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
			try (statement) {
				statement.execute();
				final ResultSet resu = statement.getResultSet();
				try (resu) {
					List<Producto> lista = new ArrayList<>();
					while (resu.next()) {
						Producto fila =new Producto(resu.getInt("ID"), resu.getString("NOMBRE"), resu.getString("DESCRIPCION"),  resu.getInt("CANTIDAD"));

						lista.add(fila);
					}
					return lista;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
