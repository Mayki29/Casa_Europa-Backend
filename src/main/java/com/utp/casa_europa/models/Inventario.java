package com.utp.casa_europa.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "inventario") // Nombre de la tabla en la base de datos
public class Inventario { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto; // Relación con Producto

    
}
