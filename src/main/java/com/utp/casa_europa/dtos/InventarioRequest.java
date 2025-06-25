package com.utp.casa_europa.dtos;

import com.utp.casa_europa.models.enums.OperacionInventario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InventarioRequest {
    private Long productoId;
    private Integer stockInv;
    private OperacionInventario operacion; 
}
