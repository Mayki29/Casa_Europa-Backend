package com.utp.casa_europa.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private List<CategoriaResponse> categorias; // Cambia a Lista de categorias a la que pertenece el producto
    private String imagenUrl; // URL de la imagen del producto
}
