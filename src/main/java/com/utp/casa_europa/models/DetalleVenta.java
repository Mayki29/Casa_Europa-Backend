package com.utp.casa_europa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="detalle_ventas")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="producto_id")
    private Producto producto;
    private BigDecimal precioUnitario;
    private BigDecimal precioConDescuento;
    private Integer cantidad;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="venta_id")
    private Venta venta;
}
