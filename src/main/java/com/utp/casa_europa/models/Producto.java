//Aquí se crean las entidades que se van a usar en la base de datos
// Se usa este archivo para definir la entidad y crear la tabla en la base de datos

package com.utp.casa_europa.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "productos") // Nombre de la tabla en la base de datos
// Aquí puedes definir los atributos de la entidad
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "creado_el")
    private LocalDateTime creado_el; // Fecha de creación del producto

    @Column(name = "imagen_url")
    private String imagenUrl; // Solo la URL o nombre del archivo

    @Column( length = 100)
    private Integer stock; // Cantidad de producto disponible

    //Relación con Categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<DetalleVenta> detalleVentas;

    @OneToMany(mappedBy = "producto")    
    private List<Inventario> inventario;
    
    
    // Constructor modificado
    public Producto(String nombre, String descripcion, BigDecimal precio, LocalDateTime creado_el, String imagenUrl, Integer stock, Categoria categoria) {
        this.creado_el = LocalDateTime.now(); // Inicializa la fecha de creación al momento de crear el producto
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.creado_el = creado_el;
        this.imagenUrl = imagenUrl;
        this.stock = stock;
        this.categoria = categoria;
    }

    // Getters and Setters
    public Producto() {
    }
}
