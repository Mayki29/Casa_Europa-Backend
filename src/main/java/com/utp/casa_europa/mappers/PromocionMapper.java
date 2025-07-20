package com.utp.casa_europa.mappers;

import com.utp.casa_europa.dtos.PromocionResponse;
import com.utp.casa_europa.models.Promocion;

public class PromocionMapper {

    public static PromocionResponse toResponse(Promocion promocion){
        return PromocionResponse.builder()
                .id(promocion.getId())
                .nombre(promocion.getNombre())
                .descripcion(promocion.getDescripcion())
                .tipo(promocion.getTipo())
                .valor(promocion.getValor())
                .codigo(promocion.getCodigo())
                .fechaInicio(promocion.getFechaInicio())
                .fechaFin(promocion.getFechaFin())
                .activa(promocion.getActiva())
                .build();
    }
}
