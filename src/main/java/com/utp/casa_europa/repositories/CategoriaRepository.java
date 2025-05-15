package com.utp.casa_europa.repositories;

import com.utp.casa_europa.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
