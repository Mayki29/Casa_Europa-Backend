package com.utp.casa_europa.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse {
    private Long id;
    private String nombre; // Nombre de la categoría
    private String descripcion;
    private String imagenUrlCat;
}
