package com.utp.casa_europa.services;

import java.time.LocalDateTime;
import java.util.List;

import com.utp.casa_europa.dtos.InventarioRequest;
import com.utp.casa_europa.dtos.InventarioResponse;
import com.utp.casa_europa.models.Inventario;
import com.utp.casa_europa.models.Producto;
import com.utp.casa_europa.repositories.InventarioRepository;
import com.utp.casa_europa.repositories.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utp.casa_europa.exceptions.EntityNotFoundException;
import com.utp.casa_europa.mappers.InventarioMapper;
import com.utp.casa_europa.models.enums.OperacionInventario;

@Service
public class InventarioService {
    @Autowired 
    private InventarioRepository inventarioRepository;
    @Autowired
    private ProductoRepository productoRepository;


    
    public InventarioResponse crearRegistroInventario(InventarioRequest inventarioRequest ){
        Producto producto = productoRepository.findById(inventarioRequest.getProductoId()).orElseThrow(
            () -> new EntityNotFoundException("Producto no encontrado")
        );

        Integer newQuantity = inventarioRequest.getStockInv();
        Integer currentStock = producto.getStock();
        Integer newStock = 0;

        switch(inventarioRequest.getOperacion()){
            case OperacionInventario.INGRESO: newStock = currentStock + newQuantity;
                                                break;
            case OperacionInventario.SALIDA: newStock = currentStock - newQuantity;
                                                break;
            case OperacionInventario.VENTA: newStock = currentStock - newQuantity;
                                                break;
        }

        producto.setStock(newStock);
        Inventario inventario = new Inventario();
        inventario.setProducto(producto);
        inventario.setCantidad(newQuantity);
        inventario.setFechaHora(LocalDateTime.now());
        inventario.setOperacion(inventarioRequest.getOperacion());

        Inventario invResponse = inventarioRepository.save(inventario);
        //productoRepository.save(producto);

        return InventarioMapper.toResponse(invResponse);
    }


    /*public Inventario actualizarInventario(InventarioRequest inventarioRequest) {
        Producto producto = productoRepository.findById(inventarioRequest.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Actualizar los detalles del producto
        producto.setStock(inventarioRequest.getStockInv());
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
    }*/
    public Inventario obtenerInventarioPorId(Long id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }
    public List<Inventario> obtenerTodosLosInventarios() {
        return inventarioRepository.findAll();
    }
    public InventarioResponse mapToResponse(Inventario inventario) {
        throw new UnsupportedOperationException("Unimplemented method 'mapToResponse'");
    }
    
}
