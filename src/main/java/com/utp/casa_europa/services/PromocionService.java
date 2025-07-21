package com.utp.casa_europa.services;

import com.utp.casa_europa.dtos.PromocionRequest;
import com.utp.casa_europa.dtos.PromocionResponse;
import com.utp.casa_europa.exceptions.EntityNotFoundException;
import com.utp.casa_europa.mappers.PromocionMapper;
import com.utp.casa_europa.models.Producto;
import com.utp.casa_europa.models.Promocion;
import com.utp.casa_europa.models.enums.TipoPromocion;
import com.utp.casa_europa.repositories.IPromocionRepository;
import com.utp.casa_europa.repositories.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromocionService {

    @Autowired
    private IPromocionRepository promocionRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public PromocionResponse findCupon(String cupon){
        Promocion promocion = promocionRepository.findPromocionByCupon(cupon);
        if(promocion == null){
            throw new EntityNotFoundException("Cupon no valido");
        }
        return PromocionMapper.toResponse(promocion);

    }
    @CacheEvict(value = "listaProducto", allEntries = true)
    public PromocionResponse crearPromocion(PromocionRequest request){

        List<Producto> productos = new ArrayList<>();
        Promocion promocion = PromocionMapper.toEntity(request);

        if(request.getTipo().equals(TipoPromocion.DESCUENTO)){
            for(Long idProductos : request.getProductosId()){
                Producto producto = productoRepository.findById(idProductos).orElseThrow(()-> new EntityNotFoundException("Producto no encontrado"));
                productos.add(producto);
            }
            promocion.setProductos(productos);
        }
        promocion.setActiva(true);

        return PromocionMapper.toResponse(promocionRepository.save(promocion));
    }

    public List<PromocionResponse> listarPromociones(){
        return promocionRepository.findAll().stream().map(PromocionMapper::toResponse).toList();
    }

    @CacheEvict(value = "listaProducto", allEntries = true)
    public PromocionResponse desactivarPromocion(Long id){
        Promocion promocion = promocionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Promocion no encontrada"));
        promocion.setActiva(false);
        return PromocionMapper.toResponse(promocionRepository.save(promocion));
    }

    @CacheEvict(value = "listaProducto", allEntries = true)
    public PromocionResponse activarPromocion(Long id){
        Promocion promocion = promocionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Promocion no encontrada"));
        promocion.setActiva(true);
        return PromocionMapper.toResponse(promocionRepository.save(promocion));
    }

}
