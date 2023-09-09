package com.alura.jdbc.modelo;

public class Producto {

    private int id;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    private String nombre;
    private String descripcion;
    private int cantidad;
    private Integer categoriaId;

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    public Producto(int id, String nombre, String descripcion, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }
    public Producto(String nombre, String descripcion, Integer cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Producto(int id, String nombre, int cantidad) {
        this.id = id;
        this.nombre = nombre; 
        this.cantidad = cantidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("{id: %s, nombre: %s, descripcion: %s ,cantidad: %d}",
                this.id,
                this.nombre,
                this.descripcion,
                this.cantidad);
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;

    }

    public int getCategoriaId() {
        return this.categoriaId;
    }
}
