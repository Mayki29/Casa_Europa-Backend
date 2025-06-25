package com.utp.casa_europa.mappers;

import com.utp.casa_europa.dtos.InventarioResponse;
import com.utp.casa_europa.models.Inventario;

public class InventarioMapper {

    public static InventarioResponse toResponse(Inventario inventario){
        return InventarioResponse.builder()
            .id(inventario.getId())
            .producto(ProductoMapper.toResponse(inventario.getProducto()))
            .cantidad(inventario.getCantidad())
            .fechaHora(inventario.getFechaHora())
            .operacion(inventario.getOperacion())
            .build();
    }
}
