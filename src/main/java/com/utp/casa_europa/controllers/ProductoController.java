// Se usa este archivo para definir el controlador de la entidad y crear los endpoints
package com.utp.casa_europa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.casa_europa.dtos.ProductoRequest;
import com.utp.casa_europa.models.Producto;
import com.utp.casa_europa.services.ProductoService;
import com.utp.casa_europa.utils.Response;


@RestController
@RequestMapping("/api/productos") // Cambia la ruta según tus necesidades
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    @GetMapping
    public ResponseEntity<?> obtenerTodosProductos() {
        List<Producto> productos = productoService.obtenerTodosProductos();
        return Response.setResponse(productos, HttpStatus.OK);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<?> obtenerProductosPorCategoria(@PathVariable Long id) {
        List<Producto> productos = productoService.obtenerProductosPorCategoria(id);
        return Response.setResponse(productos, HttpStatus.OK);
    }

    @GetMapping("/categoria/nombre/{nombreCategoria}")
    public ResponseEntity<?> obtenerProductosPorNombreCategoria(@PathVariable String nombreCategoria) {
        List<Producto> productos = productoService.obtenerProductosPorCategoria(nombreCategoria);
        return Response.setResponse(productos, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearProducto(@ModelAttribute ProductoRequest request) {
        Producto producto = productoService.crearProducto(request);
        return Response.setResponse(producto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @ModelAttribute ProductoRequest request) {
        Producto producto = productoService.actualizarProducto(id, request);
        return Response.setResponse(producto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return Response.setResponse(null, HttpStatus.NO_CONTENT);
    }
    
}
