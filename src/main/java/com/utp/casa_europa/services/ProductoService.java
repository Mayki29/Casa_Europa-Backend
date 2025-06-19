package com.utp.casa_europa.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.utp.casa_europa.dtos.CategoriaResponse;
import com.utp.casa_europa.dtos.ProductoRequest;
import com.utp.casa_europa.dtos.ProductoResponse;
import com.utp.casa_europa.exceptions.EntityNotFoundException;
import com.utp.casa_europa.models.Categoria;
import com.utp.casa_europa.models.Producto;
import com.utp.casa_europa.repositories.CategoriaRepository;
import com.utp.casa_europa.repositories.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private S3BucketService s3BucketService;

    // obtener todos los productos
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
    public ProductoResponse obtenerProductoPorIdResponse(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con ID: " + id));
        return mapToResponse(producto);
    }

    // Obtener productos por ID de categoría (usando la relación)
    public List<Producto> obtenerProductosPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    // Obtener un producto por nombre de categoria
    public List<Producto> obtenerProductosPorCategoria(String nombreCategoria) {
        return productoRepository.findByCategoriaNombre(nombreCategoria);
    }

    // Crear un nuevo producto con categoria
    public Producto crearProducto(Producto producto, Categoria categoria) {
        producto.setCategoria(categoria);
        return productoRepository.save(producto);
    }


    // ACTUALIZAR UN PRODUCTO EXISTENTE
    public ProductoResponse actualizarProducto(Long id, ProductoRequest productoActualizado) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Error mano, chécalo tal vez no existe el producto con ID: " + id));

        if (productoActualizado.getNombre() != null) {
            productoExistente.setNombre(productoActualizado.getNombre());
        }
        if (productoActualizado.getDescripcion() != null) {
            productoExistente.setDescripcion(productoActualizado.getDescripcion());
        }
        if (productoActualizado.getPrecio() != null) {
            productoExistente.setPrecio(productoActualizado.getPrecio());
        }
        if (productoActualizado.getStock() != null) {
            productoExistente.setStock(productoActualizado.getStock());
        }

        if ((productoActualizado.getCategoriaId() != null)) {
            Categoria categoriaExistente = categoriaRepository.findById(productoActualizado.getCategoriaId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Categoría no encontrada con ID: " + productoActualizado.getCategoriaId()));
            productoExistente.setCategoria(categoriaExistente);

        }
        Producto actualizado = productoRepository.save(productoExistente);
        return mapToResponse(actualizado);
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
        producto.setFecha_creacion(LocalDateTime.now());

        // Validar que la imagen no sea nula
        if (request.getImagenFile() == null || request.getImagenFile().isEmpty()) {
            throw new RuntimeException("La imagen del producto no puede ser nula o vacía.");
        }

        // Lógica para guardar la imagen y establecer la URL
        String imageName = String.format("%s_%s.%s", request.getNombre().replace(" ", ""),
                Instant.now().getEpochSecond(), getFileExtension(request.getImagenFile()));
        String imageUrl = s3BucketService.uploadFile(imageName, request.getImagenFile());
        producto.setImagenUrl(imageUrl);

        // Aquí deberías buscar la categoría por ID y establecerla en el producto
        Categoria categoria = new Categoria(); // Debes implementar la lógica para obtener la categoría por ID
        categoria.setId(request.getCategoriaId());
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    private String getFileExtension(MultipartFile file) {
        String fileContentType = file.getContentType();
        if (fileContentType == null || !fileContentType.contains("/")) {
            return ""; // o puedes lanzar una excepción personalizada si lo prefieres
        }
        return fileContentType.substring(fileContentType.indexOf("/") + 1);
    }

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
    
}
