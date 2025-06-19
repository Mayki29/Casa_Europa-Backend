package com.utp.casa_europa.dtos;

import java.math.BigDecimal;
import org.springframework.web.multipart.MultipartFile;

public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private Long categoriaId;
    private MultipartFile imagenFile;

    // Constructor
    public ProductoRequest(String nombre, String descripcion, BigDecimal precio, Integer stock, Long categoriaId, MultipartFile imagenFile) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoriaId = categoriaId;
        this.imagenFile = imagenFile;
    }

    // Getters and Setters
    
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
    
    public Long getCategoriaId() {
        return categoriaId;
    }
    
    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
    
    public MultipartFile getImagenFile() {
        return imagenFile;
    }
    public void setImagenFile(MultipartFile imagenFile) {
        this.imagenFile = imagenFile;
    }
    
    
}
