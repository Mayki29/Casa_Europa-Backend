package com.utp.casa_europa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.casa_europa.dtos.CategoriaRequest;
import com.utp.casa_europa.models.Categoria;
import com.utp.casa_europa.services.CategoriaService;
import com.utp.casa_europa.utils.Response;



@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Response<List<Categoria>>> obtenerTodasCategorias() {
        List<Categoria> categorias = categoriaService.obtenerTodasCategorias();
        return Response.setResponse(categorias, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Response<Categoria>> obtenerCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
            return Response.setResponse(categoria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<Categoria>> crearCategoria(@RequestBody CategoriaRequest request) {
        Categoria categoria = categoriaService.crearCategoria(request);
        return Response.setResponse(categoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Categoria>> actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaRequest request) {
        Categoria categoriaActualizada = categoriaService.actualizarCategoria(id, request);
        return Response.setResponse(categoriaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return Response.setResponse("Categoría eliminada correctamente", HttpStatus.NO_CONTENT);
    }


}
