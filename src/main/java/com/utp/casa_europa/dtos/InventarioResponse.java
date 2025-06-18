package com.utp.casa_europa.dtos;



public class InventarioResponse {
    private Long id;
    private ProductoResponse producto;

    public InventarioResponse() {
    }

    public InventarioResponse(Long id, ProductoResponse producto) {
        this.id = id;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductoResponse getProducto() {
        return producto;
    }

    public void setProducto(ProductoResponse producto) {
        this.producto = producto;
    }
}
