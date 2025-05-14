//Aquí se crean las entidades que se van a usar en la base de datos
// Se usa este archivo para definir la entidad y crear la tabla en la base de datos

package com.utp.casa_europa.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

// @Table(name = "usuarios") <- referenciando a la tabla que se va a usar
@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private LocalDateTime fechaCreacion;
    private String imagenUrl; // Ruta o URL de la imagen
    private String categoria; // "embutidos", "carnes", etc.
    

    public Producto(String nombre, String descripcion, BigDecimal precio, LocalDateTime fechaCreacion, String imagenUrl, String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaCreacion = fechaCreacion;
        this.imagenUrl = imagenUrl;
        this.categoria = categoria;
        
    }

    // Getters and Setters
}
