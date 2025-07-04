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

import com.utp.casa_europa.dtos.Mamamasivo;
import com.utp.casa_europa.dtos.ProductoRequest;
import com.utp.casa_europa.dtos.ProductoResponse;
import com.utp.casa_europa.models.Producto;
import com.utp.casa_europa.services.ProductoService;
import com.utp.casa_europa.utils.Response;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/api/productos") // Cambia la ruta según tus necesidades
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    // OBTIENE TODOS LOS PRODUCTOS
    @GetMapping
    public ResponseEntity<Response<List<ProductoResponse>>> obtenerTodosProductos() {
        List<ProductoResponse> productos = productoService.obtenerTodosProductosResponse();
        return Response.setResponse(productos, HttpStatus.OK);
    }

    // OBTIENE PRODUCTOS POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Response<ProductoResponse>> obtenerProductoPorId(@PathVariable Long id) {
        ProductoResponse producto = productoService.obtenerProductoPorIdResponse(id);
        return Response.setResponse(producto, HttpStatus.OK);
    }

    // OBTIENE PRODUCTOS POR CATEGORIA
    @GetMapping("/categoria/{id}")
    public ResponseEntity<Response<List<Producto>>> obtenerProductosPorCategoria(@PathVariable Long id) {
        List<Producto> productos = productoService.obtenerProductosPorCategoria(id);
        return Response.setResponse(productos, HttpStatus.OK);
    }

    @GetMapping("/categoria/nombre/{nombreCategoria}")
    public ResponseEntity<Response<List<Producto>>> obtenerProductosPorNombreCategoria(@PathVariable String nombreCategoria) {
        List<Producto> productos = productoService.obtenerProductosPorCategoria(nombreCategoria);
        return Response.setResponse(productos, HttpStatus.OK);
    }

    // CREAR NUEVO PRODUCTO
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response<ProductoResponse>> crearProducto(@RequestBody ProductoRequest request) {
        ProductoResponse producto = productoService.crearProducto(request);
        return Response.setResponse(producto, HttpStatus.CREATED);
    }

    //ACTUALIZAR PRODUCTO EXISTENTE
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response<ProductoResponse>> actualizarProducto(@PathVariable Long id, @ModelAttribute ProductoRequest request) {
        ProductoResponse productoActualizado = productoService.actualizarProducto(id, request);
        return Response.setResponse(productoActualizado, HttpStatus.OK);
    }
    

    // ELIMINAR PRODUCTO EXISTENTE POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return Response.setResponse(null, HttpStatus.NO_CONTENT);
    }

    // ACTUALIZAR CATEGORÍAS EN MASA
    @PostMapping(value = "/actualizacion-masiva", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> actualizarCategoriasMasivo(@ModelAttribute Mamamasivo request) {
        productoService.actualizarCategoriasEnMasa(request.getProductoIds(), request.getCategoriaIds());
        return ResponseEntity.ok("Categorías actualizadas en masa");
    }
    
}
