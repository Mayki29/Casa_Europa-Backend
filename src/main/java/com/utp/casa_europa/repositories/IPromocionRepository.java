package com.utp.casa_europa.repositories;

import com.utp.casa_europa.models.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface IPromocionRepository extends JpaRepository<Promocion,Long> {

    @Procedure(procedureName = "sp_find_promocion_by_producto")
    Promocion findPromocionByProductId(@Param("producto_id") Long id);

    @Procedure(procedureName = "sp_find_promocion_by_cupon")
    Promocion findPromocionByCupon(@Param("cupon") String cupon);
}
