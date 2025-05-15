package com.utp.casa_europa.repositories;

import com.utp.casa_europa.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Aquí puedes definir métodos para interactuar con la base de datos
    // Por ejemplo, si estás usando JPA, puedes extender JpaRepository o CrudRepository

    // Buscar productos por nombre de categoría
    List<Producto> findByCategoriaNombre(String nombreCategoria);

    // Buscar productos por ID de categoría
    List<Producto> findByCategoriaId(Long categoriaId);
    
}
