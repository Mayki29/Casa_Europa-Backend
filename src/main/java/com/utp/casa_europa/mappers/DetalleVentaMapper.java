package com.utp.casa_europa.mappers;

import com.utp.casa_europa.dtos.DetalleVentaDto;
import com.utp.casa_europa.models.DetalleVenta;
import com.utp.casa_europa.models.Producto;

public class DetalleVentaMapper {

    public static DetalleVenta dtoToEntity(DetalleVentaDto detalleVentaDto){
        return DetalleVenta.builder()
                .producto(Producto.builder().id(detalleVentaDto.getIdProducto()).build())
                .cantidad(detalleVentaDto.getCantidad())
                .build();
    }
}
