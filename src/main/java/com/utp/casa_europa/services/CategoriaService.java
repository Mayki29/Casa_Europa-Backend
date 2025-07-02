package com.utp.casa_europa.services;

import java.util.List;
import java.util.ArrayList;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.utp.casa_europa.dtos.CategoriaRequest;
import com.utp.casa_europa.exceptions.EntityNotFoundException;
import com.utp.casa_europa.repositories.CategoriaRepository;
import com.utp.casa_europa.models.Categoria;
import com.utp.casa_europa.models.Producto;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private S3BucketService s3BucketService;
    
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
    

    // CREAR CATEGORIA
    public Categoria crearCategoria(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());

        // Validar que la imagen no sea nula
        if (request.getImagenCat() == null || request.getImagenCat().isEmpty()) {
            throw new RuntimeException("La imagen de la categoría no puede ser nula o vacía");
        }
        // Guardar imagen y establecer URL
        String imagenName = String.format("%s_%s.%s", request.getNombre().replace(" ",""),
                Instant.now().getEpochSecond(), getFileExtension(request.getImagenCat()));
        String imagenUrl = s3BucketService.uploadFile(imagenName, request.getImagenCat());
        categoria.setImagenUrlCat(imagenUrl);
        // Verifica si ya existe una categoría con el mismo nombre
        List<Categoria> categoriasExistentes = categoriaRepository.findByNombre(request.getNombre());
        if (!categoriasExistentes.isEmpty()) {
            throw new RuntimeException("Ya existe una categoría con el nombre: " + request.getNombre());
        }

        return categoriaRepository.save(categoria);
    }
    //implementando método para multiPartFile
    private String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        throw new RuntimeException("El nombre del archivo no tiene una extensión válida");
    }

    // ACTUALIZAR CATEGORIA
    public Categoria actualizarCategoria(Long id, CategoriaRequest request) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe esta categoría: " + id));

        if (request.getNombre() != null) {
            categoriaExistente.setNombre(request.getNombre());
        }
        if (request.getDescripcion() != null) {
            categoriaExistente.setDescripcion(request.getDescripcion());
        }
        if (request.getImagenCat() != null && !request.getImagenCat().isEmpty()) {
            // Validar que la imagen no sea nula
            String imagenName = String.format("%s_%s.%s", request.getNombre().replace(" ", ""),
                    Instant.now().getEpochSecond(), getFileExtension(request.getImagenCat()));
            String imagenUrl = s3BucketService.uploadFile(imagenName, request.getImagenCat());
            categoriaExistente.setImagenUrlCat(imagenUrl);
        }

        return categoriaRepository.save(categoriaExistente);
    }

    // ELIMINAR CATEGORIA
    public void eliminarCategoria(Long id) {
        Categoria categoria = obtenerCategoriaPorId(id);
        if (categoria != null) {
            // Se reasignan los productos a una categoría por defecto antes de eliminar
            Categoria categoriaDefault = categoriaRepository.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("Categoría por defecto no encontrada"));
            List<Producto> productos = categoria.getProductos();
            for (Producto producto : productos) {
                List<Categoria> nuevasCategorias = new ArrayList<>();
                nuevasCategorias.add(categoriaDefault);
                producto.setCategorias(nuevasCategorias);

            }
            categoriaRepository.delete(categoria);
        } else {
            throw new EntityNotFoundException("Categoría no encontrada");
        }
    }

}
