package com.utp.casa_europa.services;

import com.utp.casa_europa.models.Producto;
import com.utp.casa_europa.models.Categoria;
import com.utp.casa_europa.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    //obtener todos los productos
    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }

    // Obtener productos por ID de categoría (usando la relación)
    public List<Producto> obtenerProductosPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    //Obtener un producto por nombre de categoria
    public List<Producto> obtenerProductosPorCategoria(String nombreCategoria) {
        return productoRepository.findByCategoriaNombre(nombreCategoria);
    }

    // Crear un nuevo producto con categoria
    public Producto crearProducto(Producto producto, Categoria categoria) {
        producto.setCategoria(categoria);
        return productoRepository.save(producto);
    }
}
