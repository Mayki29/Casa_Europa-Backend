package com.utp.casa_europa.services;

import com.utp.casa_europa.models.Entidad;
import com.utp.casa_europa.repositories.EntidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntidadService {
    private final EntidadRepository entidadRepository;
    
    public EntidadService(EntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    public List<Entidad> obtenerTodasEntidades() {
        return entidadRepository.findAll();
    }
}
