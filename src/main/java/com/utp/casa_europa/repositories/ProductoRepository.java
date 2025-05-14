package com.utp.casa_europa.repositories;

import com.utp.casa_europa.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Aquí puedes definir métodos para interactuar con la base de datos
    // Por ejemplo, si estás usando JPA, puedes extender JpaRepository o CrudRepository

    List<Producto> findByCategoria(String categoria);
    
}
