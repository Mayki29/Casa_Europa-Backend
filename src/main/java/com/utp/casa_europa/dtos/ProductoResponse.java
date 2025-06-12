package com.utp.casa_europa.dtos;

import java.math.BigDecimal;


public class ProductoResponse {
    
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private CategoriaResponse categoriaId; // ID de la categoría a la que pertenece el producto
    private String imagenUrl; // URL de la imagen del producto

    public ProductoResponse() {
    }

    public ProductoResponse(Long id, String nombre, String descripcion, BigDecimal precio, Integer stock, String imagenUrl, CategoriaResponse categoriaId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagenUrl = imagenUrl;
        this.categoriaId = categoriaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public CategoriaResponse getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(CategoriaResponse categoriaId) {
        this.categoriaId = categoriaId;
    }
}
