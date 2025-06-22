package com.utp.casa_europa.dtos;

import java.time.LocalDateTime;

import com.utp.casa_europa.models.enums.OperacionInventario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventarioResponse {
    private Long id;
    private ProductoResponse producto;
    private Integer cantidad;
    private LocalDateTime fechaHora;
    private OperacionInventario operacion;
}
