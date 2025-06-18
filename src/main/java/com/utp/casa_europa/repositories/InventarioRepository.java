package com.utp.casa_europa.repositories;

import com.utp.casa_europa.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
