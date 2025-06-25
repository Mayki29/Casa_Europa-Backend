package com.utp.casa_europa.mappers;


import com.utp.casa_europa.dtos.ProductoResponse;
import com.utp.casa_europa.models.Producto;

public class ProductoMapper {

    public static ProductoResponse toResponse(Producto producto){
        return ProductoResponse.builder()
            .id(producto.getId())
            .nombre(producto.getNombre())
            .descripcion(producto.getDescripcion())
            .precio(producto.getPrecio())
            .stock(producto.getStock())
            .categoria(CategoriaMapper.toResponse(producto.getCategoria()))
            .imagenUrl(producto.getImagenUrl())
            .build();

    }
}
