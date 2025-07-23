package com.utp.casa_europa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DashboardRepository extends CrudRepository<Object, Long> {
    @Query(value = "SELECT p.nombre FROM detalle_ventas dv JOIN productos p ON p.id = dv.producto_id GROUP BY p.id ORDER BY SUM(dv.cantidad) DESC LIMIT 1", nativeQuery = true)
    String findProductoMasVendido();

    @Query(value = "SELECT COUNT(DISTINCT usuario_id) FROM ventas", nativeQuery = true)
    Integer findTotalClientes();

    @Query(value = "SELECT c.nombre FROM detalle_ventas dv JOIN productos p ON p.id = dv.producto_id JOIN categorias c ON c.id = p.categoria_id GROUP BY c.id ORDER BY SUM(dv.cantidad) DESC LIMIT 1", nativeQuery = true)
    String findCategoriaMasVendida();

    @Query(value = "SELECT DATE(v.fecha_hora) AS fecha, SUM(v.total_con_descuento) AS total FROM ventas v WHERE v.fecha_hora >= CURDATE() - INTERVAL 7 DAY GROUP BY fecha ORDER BY fecha", nativeQuery = true)
    List<Object[]> findVentasPorDia();
}
