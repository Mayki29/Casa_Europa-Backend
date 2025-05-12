package com.utp.casa_europa.repositories;

import com.utp.casa_europa.models.Entidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadRepository extends JpaRepository<Entidad, Long> {

    // Aquí puedes definir métodos para interactuar con la base de datos
    // Por ejemplo, si estás usando JPA, puedes extender JpaRepository o CrudRepository
    
}
