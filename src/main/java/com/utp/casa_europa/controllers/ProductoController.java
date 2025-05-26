// Se usa este archivo para definir el controlador de la entidad y crear los endpoints
package com.utp.casa_europa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.utp.casa_europa.models.Producto;
import com.utp.casa_europa.services.ProductoService;
import com.utp.casa_europa.dtos.ProductoRequest;


@RestController
@RequestMapping("/api/productos") // Cambia la ruta según tus necesidades
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosProductos() {
        List<Producto> productos = productoService.obtenerTodosProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable Long id) {
        List<Producto> productos = productoService.obtenerProductosPorCategoria(id);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/categoria/nombre/{nombreCategoria}")
    public ResponseEntity<List<Producto>> obtenerProductosPorNombreCategoria(@PathVariable String nombreCategoria) {
        List<Producto> productos = productoService.obtenerProductosPorCategoria(nombreCategoria);
        return ResponseEntity.ok(productos);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Producto> crearProducto(@ModelAttribute ProductoRequest request) {
        Producto producto = productoService.crearProducto(request);
        return ResponseEntity.status(201).body(producto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @ModelAttribute ProductoRequest request) {
        Producto producto = productoService.actualizarProducto(id, request);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
    
}
