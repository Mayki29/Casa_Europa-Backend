package com.utp.casa_europa.dtos;

import com.utp.casa_europa.models.enums.TipoPromocion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromocionRequest {
    private Long id;
    private String nombre;
    private String descripcion;
    private TipoPromocion tipo;
    private BigDecimal valor;
    private String codigo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Long> productosId;

}
