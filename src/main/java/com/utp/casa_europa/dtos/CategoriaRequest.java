package com.utp.casa_europa.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoriaRequest {
    
    private Long id;
    private String nombre; // Nombre de la categoría
    private String descripcion;
    private MultipartFile imagenCat;

    
}
