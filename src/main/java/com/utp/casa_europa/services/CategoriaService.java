package com.utp.casa_europa.services;

import com.utp.casa_europa.dtos.CategoriaRequest;
import com.utp.casa_europa.models.Categoria;
import com.utp.casa_europa.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    public Categoria crearCategoria(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
        return categoriaRepository.save(categoria);
    }
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }
}
