package com.alura.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Properties;

import com.alura.jdbc.controller.CategoryController;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Category;
import com.alura.jdbc.modelo.Product;
import com.dao.ProductDAO;
import com.util.ApplicationConfigLoader;
import net.datafaker.Faker;

public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
//        Properties props = ApplicationConfigLoader.loadConfig();
//        for (String key : props.stringPropertyNames()) {
//            System.out.println(key + "=" + props.getProperty(key));
//            }
//        Connection con = new ConnectionFactory().getConnection();
//        System.out.println(con.getMetaData().getSchemas());
//        System.out.println("Cerrando la conexión");
        Connection c = new ConnectionFactory().getConnection();
        for (int i = 0; i < 10; i++) {
            Faker faker = new Faker(new Locale("es", "mx"));
            var st = c.prepareStatement("INSERT INTO categorias (nombre) VALUES (?)");
            var s = faker.commerce().department();
            st.setString(1, s);
            st.execute();
        }
//        for (int i = 0; i < 500; i++) {
//        poblarDB(c);
//        }
    }


    private static void poblarDB(Connection connection) {
        Faker faker = new Faker(new Locale("es", "mx"));
        ProductDAO productDAO = new ProductDAO(connection);
        var p = new Product(
                faker.commerce().productName(),
                faker.commerce().material(),
                faker.number().positive()
        );
        p.setCategoryId(faker.number().numberBetween(1, 10));
        productDAO.add(p);
    }
}