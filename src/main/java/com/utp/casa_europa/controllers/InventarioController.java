package com.utp.casa_europa.controllers;

import com.utp.casa_europa.dtos.InventarioRequest;
import com.utp.casa_europa.models.Inventario;
import com.utp.casa_europa.services.InventarioService;
import com.utp.casa_europa.dtos.InventarioResponse;

import com.utp.casa_europa.utils.Response;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @PostMapping("/actualizar")
    public ResponseEntity<Response<InventarioResponse>> actualizarInventario(@RequestBody InventarioRequest inventarioRequest) {
        Inventario inventario = inventarioService.actualizarInventario(inventarioRequest);
        InventarioResponse inventarioResponse = inventarioService.mapToResponse(inventario);
        return Response.setResponse(inventarioResponse, HttpStatus.OK);
    }

    // Aquí puedes agregar más métodos para manejar otras operaciones relacionadas con el inventario
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerInventarioPorId(@PathVariable Long id) {
        Inventario inventario = inventarioService.obtenerInventarioPorId(id);
        if (inventario != null) {
            return Response.setResponse(inventario, HttpStatus.OK);
        } else {
            return Response.setResponse("Inventario no encontrado", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<?> obtenerTodosLosInventarios() {
        return Response.setResponse(inventarioService.obtenerTodosLosInventarios(), HttpStatus.OK);
    }
    
}
