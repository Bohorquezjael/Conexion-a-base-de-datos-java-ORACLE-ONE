package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

public class ProductoDAO {
    final private Connection con;
    public ProductoDAO(Connection con){
        this.con = con;
    }
    public void guardar(Producto producto) throws SQLException{
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
