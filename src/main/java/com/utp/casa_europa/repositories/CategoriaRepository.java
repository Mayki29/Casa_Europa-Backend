package com.utp.casa_europa.repositories;

import com.utp.casa_europa.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNombre(String nombre);
}
