package com.utp.casa_europa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDto {
    private Integer idProducto;
    private Integer cantidad;
}
