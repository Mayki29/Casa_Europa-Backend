package com.utp.casa_europa.services;

import com.utp.casa_europa.dtos.PromocionResponse;
import com.utp.casa_europa.exceptions.EntityNotFoundException;
import com.utp.casa_europa.mappers.PromocionMapper;
import com.utp.casa_europa.models.Promocion;
import com.utp.casa_europa.repositories.IPromocionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocionService {

    @Autowired
    private IPromocionRepository promocionRepository;

    @Transactional
    public PromocionResponse findCupon(String cupon){
        Promocion promocion = promocionRepository.findPromocionByCupon(cupon);
        if(promocion == null){
            throw new EntityNotFoundException("Cupon no valido");
        }
        return PromocionMapper.toResponse(promocion);

    }
}
