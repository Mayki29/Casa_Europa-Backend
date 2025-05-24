package com.utp.casa_europa.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    private String dni;

    @Column(unique=true)
    private String email;

    private String telefono;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    @Enumerated(EnumType.STRING)
    private Estado estado;


    @OneToMany(mappedBy="usuario", fetch=FetchType.LAZY)
    private List<Token> tokens;

    @OneToMany(mappedBy="usuario", fetch=FetchType.LAZY)
    private List<Venta> ventas;


}
