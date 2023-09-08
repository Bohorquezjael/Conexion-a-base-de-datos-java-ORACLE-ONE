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
            final PreparedStatement decla = con.prepareStatement("SELECT ID, NOMBRE FROM CATEGORIAS");
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
