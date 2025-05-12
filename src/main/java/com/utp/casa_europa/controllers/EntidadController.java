package com.utp.casa_europa.controllers;

import com.utp.casa_europa.models.Entidad;
import com.utp.casa_europa.services.EntidadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/entidades")
public class EntidadController {
    // Aquí puedes definir los métodos para manejar las solicitudes HTTP
    // Por ejemplo, puedes usar @GetMapping, @PostMapping, etc. para definir los endpoints
    // y usar el servicio EntidadService para interactuar con la base de datos
    private final EntidadService entidadService;
    
    public EntidadController(EntidadService usuarioService) {
        this.entidadService = usuarioService;
    }
    
    @GetMapping
    public List<Entidad> obtenerTodasEntidades() {
        return entidadService.obtenerTodasEntidades();
    }
}
