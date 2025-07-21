package com.utp.casa_europa.models;

import com.utp.casa_europa.models.enums.TipoPromocion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name ="promociones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private TipoPromocion tipo;

    private BigDecimal valor;
    private String codigo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private Boolean activa;

    @ManyToMany
    @JoinTable(
            name = "promocion_productos",
            joinColumns = @JoinColumn(name = "promocion_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;

    @OneToMany(mappedBy = "promocion")
    private List<Venta> ventas;

}
