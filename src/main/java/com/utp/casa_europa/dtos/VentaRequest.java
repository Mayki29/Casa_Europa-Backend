package com.utp.casa_europa.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaRequest {
    private Long usuarioId;
    private List<DetalleVentaDto> detalleVenta;
    private String cuponDescuento;

}
