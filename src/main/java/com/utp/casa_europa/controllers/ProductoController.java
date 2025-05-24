// Se usa este archivo para definir el controlador de la entidad y crear los endpoints
package com.utp.casa_europa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.casa_europa.models.Producto;
import com.utp.casa_europa.services.ProductoService;


@RestController
@RequestMapping("/api/productos") // Cambia la ruta según tus necesidades
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    @GetMapping
    public List<Producto> obtenerTodosProductos() {
        return productoService.obtenerTodosProductos();
    }

    @GetMapping("/categoria/{id}")
    public List<Producto> obtenerProductosPorCategoria(@PathVariable Long id) {
        return productoService.obtenerProductosPorCategoria(id);
    }
    @GetMapping("/categoria/nombre/{nombreCategoria}")
    public List<Producto> obtenerProductosPorNombreCategoria(@PathVariable String nombreCategoria) {
        return productoService.obtenerProductosPorCategoria(nombreCategoria);
    }
    
}
