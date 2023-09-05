package com.alura.jdbc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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

	public int modificar(Producto producto) throws SQLException {
		return productoDAO.modificar();
	}

	public int eliminar(Integer id) {
		return productoDAO.eliminar(id);
	}
	public List<Producto> listar() {
		return productoDAO.listar(); 
		
	}

	public void guardar(Producto producto) {
		productoDAO.guardar(producto);
	}
}