package com.utp.casa_europa.dtos;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private Long categoriaId;
    private MultipartFile imagen;

}
