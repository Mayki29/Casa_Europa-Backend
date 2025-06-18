package com.utp.casa_europa.dtos;

import java.math.BigDecimal;

public class InventarioRequest {
    private Long productoId;
    private Integer stockInv;
    private String nombreInv; 
    private BigDecimal precioInv;
    private Long categoriaId;

    public InventarioRequest() {
    }
    public InventarioRequest(Long productoId, Integer stock, String nombre, BigDecimal precio, Long categoriaId) {
        this.productoId = productoId;
        this.stock = stock;
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaId = categoriaId;
    }
    public Long getProductoId() {
        return productoId;
    }
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public Long getCategoriaId() {
        return categoriaId;
    }
    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }


}
