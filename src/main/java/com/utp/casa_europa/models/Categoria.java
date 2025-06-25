package com.utp.casa_europa.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categorias") // Nombre de la tabla en la base de datos
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre; // Nombre de la categoría (ej. "embutidos", "carnes", etc.)
    
    @Column(columnDefinition =  "TEXT")
    private String descripcion; // Descripción de la categoría

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl; // URL de la imagen de la categoría (opcional)

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Producto> productos; // Relación con la entidad Producto
}