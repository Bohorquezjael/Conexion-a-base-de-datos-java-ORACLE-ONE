package com.alura.jdbc.modelo;

public class Product {

    private int id;
    private String name;
    private String description;
    private int quantity;
    private Integer categoryId;

    public Product(int id, String name, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Product(String name, String description, Integer quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Product(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, description: %s ,quantity: %d}",
                this.id,
                this.name,
                this.description,
                this.quantity);
    }
}
