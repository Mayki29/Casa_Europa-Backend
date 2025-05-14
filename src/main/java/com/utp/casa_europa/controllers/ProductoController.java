// Se usa este archivo para definir el controlador de la entidad y crear los endpoints
package com.utp.casa_europa.controllers;

import com.utp.casa_europa.models.Producto;
import com.utp.casa_europa.services.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/productos") // Cambia la ruta según tus necesidades
public class ProductoController {
    // Aquí puedes definir los métodos para manejar las solicitudes HTTP
    // Por ejemplo, puedes usar @GetMapping, @PostMapping, etc. para definir los endpoints
    // y usar el servicio EntidadService para interactuar con la base de datos

    @Autowired
    private ProductoService productoService;
    
    // Endpoint para obtener todos los productos
    // Puedes usar @GetMapping para manejar solicitudes GET
    // Puedes usar @PostMapping para manejar solicitudes POST
    
    @GetMapping
    public List<Producto> obtenerTodosProductos() {
        return productoService.obtenerTodosProductos();
    }

    @GetMapping("/categoria/{categoria}")
    public List<Producto> obtenerPorCategoria(@PathVariable String categoria) {
        return productoService.obtenerPorCategoria(categoria);
    }
    
}
