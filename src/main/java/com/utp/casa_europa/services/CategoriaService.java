package com.utp.casa_europa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utp.casa_europa.dtos.CategoriaRequest;
import com.utp.casa_europa.exceptions.EntityNotFoundException;
import com.utp.casa_europa.repositories.CategoriaRepository;
import com.utp.casa_europa.models.Categoria;
import com.utp.casa_europa.models.Producto;

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
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria == null) {
            throw new EntityNotFoundException("Categoría no encontrada");
        }
        return categoria;
    }

    public List<Categoria> obtenerTodasCategorias() {
        return categoriaRepository.findAll();
    }

    // ACTUALIZAR CATEGORIA
    public Categoria actualizarCategoria(Long id, CategoriaRequest request) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Solo actualiza el nombre si viene en el request
        if (request.getNombre() != null) {
            categoriaExistente.setNombre(request.getNombre());
        }
        // Solo actualiza la descripción si viene en el request
        if (request.getDescripcion() != null) {
            categoriaExistente.setDescripcion(request.getDescripcion());
        }
        return categoriaRepository.save(categoriaExistente);
    }

    public void eliminarCategoria(Long id) {
        Categoria categoria = obtenerCategoriaPorId(id);
        if (categoria != null) {
            // Se reasignan los productos a una categoría por defecto antes de eliminar
            Categoria categoriaDefault = categoriaRepository.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("Categoría por defecto no encontrada"));
            List<Producto> productos = categoria.getProductos();
            for (Producto producto : productos) {
                producto.setCategoria(categoriaDefault);

            }
            categoriaRepository.delete(categoria);
        } else {
            throw new EntityNotFoundException("Categoría no encontrada");
        }
    }

}
