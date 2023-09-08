package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
					"INSERT INTO PRODUCTO(NOMBRE, DESCRIPCION, CANTIDAD, CATEGORIA_ID) VALUES( ?, ?, ?, ?)",
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

	public int eliminar(Integer id) {
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
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

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
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
