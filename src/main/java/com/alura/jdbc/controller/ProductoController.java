package com.alura.jdbc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;
import com.dao.ProductoDAO;

public class ProductoController {
	private ProductoDAO productoDAO;

	/**
	 * @param productoDAO
	 */
	public ProductoController() {
		this.productoDAO = productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
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
	public List<Producto> listar() {
		return productoDAO.listar(); 
		
	}

	public void guardar(Producto producto) {
		productoDAO.guardar(producto);
	}
}