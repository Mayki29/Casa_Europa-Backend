package com.utp.casa_europa.models;

import java.time.LocalDateTime;

import com.utp.casa_europa.models.enums.OperacionInventario;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventario") // Nombre de la tabla en la base de datos
public class Inventario { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto; // Relación con Producto

    private Integer cantidad;
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    private OperacionInventario operacion;

}
