package com.utp.casa_europa.dtos;

import com.utp.casa_europa.models.enums.TipoPromocion;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromocionResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private TipoPromocion tipo;
    private BigDecimal valor;
    private String codigo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Boolean activa;
}
