package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.modelo.Categoria;

public class CategoriaDAO {

    private Connection con;
    public CategoriaDAO(Connection con) {
        this.con = con;
    }

    public List<Categoria> listar() {
        List<Categoria> result = new ArrayList<>();
        try {
            var querySelect = "SELECT ID, NOMBRE FROM CATEGORIAS";
            System.out.println(querySelect);
            final PreparedStatement decla = con.prepareStatement(querySelect);
            try(decla){
            final ResultSet resu = decla.executeQuery();
            try(resu){
                while (resu.next()) {
                    var categoria = new Categoria(resu.getInt("ID"),
                    resu.getString("NOMBRE"));
                    
                    result.add(categoria);
                }
            }
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Categoria> listarConProductos() {

 List<Categoria> result = new ArrayList<>();
        try {
            var querySelect = "SELECT C.ID, P.NOMBRE, P.DESCRIPCION, P.CANTIDAD ,C.NOMBRE FROM CATEGORIAS C INNER JOIN PRODUCTO P ON C.ID = P.CATEGORIA_ID";
            System.out.println(querySelect);
            final PreparedStatement decla = con.prepareStatement(querySelect);
            try(decla){
            final ResultSet resu = decla.executeQuery();
            try(resu){
                while (resu.next()) {
                    var categoria = new Categoria(resu.getInt("ID"),
                    resu.getString("NOMBRE"));
                    
                    result.add(categoria);
                }
            }
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;   
    }
    
}
