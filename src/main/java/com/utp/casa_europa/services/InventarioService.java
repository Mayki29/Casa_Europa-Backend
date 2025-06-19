package com.utp.casa_europa.services;

import com.utp.casa_europa.dtos.InventarioRequest;
import com.utp.casa_europa.dtos.InventarioResponse;
import com.utp.casa_europa.models.Categoria;
import com.utp.casa_europa.models.Inventario;
import com.utp.casa_europa.models.Producto;
import com.utp.casa_europa.repositories.CategoriaRepository;
import com.utp.casa_europa.repositories.InventarioRepository;
import com.utp.casa_europa.repositories.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InventarioService {
    @Autowired 
    private InventarioRepository inventarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Inventario actualizarInventario(InventarioRequest inventarioRequest) {
        Producto producto = productoRepository.findById(inventarioRequest.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Actualizar los detalles del producto
        producto.setStock(inventarioRequest.getStock());
        producto.setNombre(inventarioRequest.getNombre());
        producto.setPrecio(inventarioRequest.getPrecio());

        if (inventarioRequest.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(inventarioRequest.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            producto.setCategoria(categoria);
        }
        // Guardar el producto actualizado
        producto = productoRepository.save(producto);

        Inventario inventario = new Inventario();
        inventario.setProducto(producto);
        return inventarioRepository.save(inventario);
    }
    public Inventario obtenerInventarioPorId(Long id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }
    public Iterable<Inventario> obtenerTodosLosInventarios() {
        return inventarioRepository.findAll();
    }
    public InventarioResponse mapToResponse(Inventario inventario) {
        throw new UnsupportedOperationException("Unimplemented method 'mapToResponse'");
    }
    
    
}
