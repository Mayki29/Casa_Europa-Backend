package com.utp.casa_europa.controllers;

import com.utp.casa_europa.dtos.CategoriaRequest;
import com.utp.casa_europa.models.Categoria;
import com.utp.casa_europa.services.CategoriaService;
import com.utp.casa_europa.utils.Response;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<?> crearCategoria(@RequestBody CategoriaRequest request) {
        Categoria categoria = categoriaService.crearCategoria(request);
        return Response.setResponse(categoria, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        if (categoria != null) {
            return Response.setResponse(categoria, HttpStatus.OK);
        } else {
            return Response.setResponse("Categoría no encontrada", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<?> obtenerTodasCategorias() {
        List<Categoria> categorias = categoriaService.obtenerTodasCategorias();
        return Response.setResponse(categorias, HttpStatus.OK);
    }
    
    
    // Aquí puedes implementar los métodos del controlador para manejar las solicitudes HTTP relacionadas con las categorías
    // Por ejemplo, métodos para crear, actualizar, eliminar y listar categorías

    // Ejemplo de método para obtener todas las categorías
    // @GetMapping("/categorias")
    // public List<Categoria> getAllCategorias() {
    //     return categoriaService.getAllCategorias();
    // }

    // Ejemplo de método para crear una nueva categoría
    // @PostMapping("/categorias")
    // public Categoria createCategoria(@RequestBody CategoriaRequest categoriaRequest) {
    //     return categoriaService.createCategoria(categoriaRequest);
    // }


}
