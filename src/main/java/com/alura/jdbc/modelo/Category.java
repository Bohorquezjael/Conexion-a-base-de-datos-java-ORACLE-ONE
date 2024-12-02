package com.alura.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Category {
    
    private Integer id;
    private String name;
    private List<Product> products;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public List<Product> getProducts() {
        return this.products;
    }
    
    public void add(Product product) {
        if(this.products == null){
            this.products = new ArrayList<>();
        }
        this.products.add(product);
    }

    @Override
    public String toString(){
        return this.name;
    }
}
