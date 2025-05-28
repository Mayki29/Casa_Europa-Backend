package com.utp.casa_europa.services;

import java.time.LocalDateTime;

import com.utp.casa_europa.models.Producto;
import com.utp.casa_europa.models.Categoria;
import com.utp.casa_europa.repositories.ProductoRepository;
import com.utp.casa_europa.dtos.ProductoRequest;
import com.utp.casa_europa.dtos.ProductoResponse;
import com.utp.casa_europa.dtos.CategoriaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private ProductoResponse mapToResponse(Producto producto) {
        ProductoResponse response = new ProductoResponse();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());
        response.setImagenUrl(producto.getImagenUrl());

        CategoriaResponse catResp = new CategoriaResponse();
        catResp.setId(producto.getCategoria().getId());
        catResp.setNombre(producto.getCategoria().getNombre());
        catResp.setDescripcion(producto.getCategoria().getDescripcion());

        response.setCategoriaId(catResp);
        return response;
    }
    
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private S3BucketService s3BucketService;

    //obtener todos los productos
    public List<ProductoResponse> obtenerTodosProductosResponse() {
        return productoRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .toList();
    }
    // Obtener todos los productos
    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }
    // Obtener un producto por ID
    public ProductoResponse obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return mapToResponse(producto);
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
    // Actualizar un producto existente
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        
        // Actualizar los campos del producto existente
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setImagenUrl(productoActualizado.getImagenUrl());
        productoExistente.setCategoria(productoActualizado.getCategoria());

        return productoRepository.save(productoExistente);
    }
    // Eliminar un producto por ID
    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        productoRepository.delete(producto);
    }
    // Crear un nuevo producto con una solicitud DTO
    public Producto crearProducto(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        
        // Aquí puedes manejar la imagen si es necesario
        if (request.getImagen() != null && !request.getImagen().isEmpty()) {
            
        }

        // Lógica para guardar la imagen y establecer la URL
        String imageName = request.getNombre() +"_"+LocalDateTime.now().toString();
        String imageUrl = s3BucketService.uploadFile(imageName, request.getImagen());
        producto.setImagenUrl(imageUrl);
        
        // Aquí deberías buscar la categoría por ID y establecerla en el producto
        Categoria categoria = new Categoria(); // Debes implementar la lógica para obtener la categoría por ID
        categoria.setId(request.getCategoriaId());
        producto.setCategoria(categoria);
        
        return productoRepository.save(producto);
    }
    // Actualizar un producto con una solicitud DTO
    public Producto actualizarProducto(Long id, ProductoRequest request) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        
        productoExistente.setNombre(request.getNombre());
        productoExistente.setDescripcion(request.getDescripcion());
        productoExistente.setPrecio(request.getPrecio());
        productoExistente.setStock(request.getStock());
        
    
        if (request.getImagen() != null && !request.getImagen().isEmpty()) {
            String imagenUrl = "src/resources/static/images";
            productoExistente.setImagenUrl(imagenUrl);
        }
        
        // Aquí deberías buscar la categoría por ID y establecerla en el producto
        Categoria categoria = new Categoria(); // Debes implementar la lógica para obtener la categoría por ID
        categoria.setId(request.getCategoriaId());
        productoExistente.setCategoria(categoria);
        
        return productoRepository.save(productoExistente);
    }
}
