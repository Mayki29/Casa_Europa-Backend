package com.utp.casa_europa.services;

import java.util.List;
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
    public List<Categoria> obtenerTodasCategorias() {
        return categoriaRepository.findAll();
    }
    // Aquí puedes implementar otros métodos del servicio para manejar las operaciones relacionadas con las categorías
    // Por ejemplo, métodos para actualizar, eliminar y buscar categorías por nombre
    public Categoria actualizarCategoria(Long id, CategoriaRequest request) {
        Categoria categoria = obtenerCategoriaPorId(id);
        if (categoria != null) {
            categoria.setNombre(request.getNombre());
            categoria.setDescripcion(request.getDescripcion());
            return categoriaRepository.save(categoria);
        }
        return null; // O lanzar una excepción si no se encuentra la categoría
    }
    public void eliminarCategoria(Long id) {
        Categoria categoria = obtenerCategoriaPorId(id);
        if (categoria != null) {
            categoriaRepository.delete(categoria);
        } else {
            throw new RuntimeException("Categoría no encontrada con ID: " + id);
        }
    }
}
