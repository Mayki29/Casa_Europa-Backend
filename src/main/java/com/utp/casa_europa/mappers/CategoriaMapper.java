package com.utp.casa_europa.mappers;

import com.utp.casa_europa.dtos.CategoriaResponse;
import com.utp.casa_europa.models.Categoria;

public class CategoriaMapper {

    public static CategoriaResponse toResponse(Categoria categoria){
        return CategoriaResponse.builder()
            .id(categoria.getId())
            .nombre(categoria.getNombre())
            .descripcion(categoria.getDescripcion())
            .imagenUrlCat(categoria.getImagenUrlCat()) // Asumiendo que Categoria tiene un campo imagenUrlCat
            .build();
    }
}
