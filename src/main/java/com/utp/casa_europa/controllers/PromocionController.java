package com.utp.casa_europa.controllers;

import com.utp.casa_europa.dtos.PromocionResponse;
import com.utp.casa_europa.services.PromocionService;
import com.utp.casa_europa.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/promocion")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @GetMapping("/validar_cupon/{cupon}")
    public ResponseEntity<Response<PromocionResponse>> findPromocion(@PathVariable String cupon){
        PromocionResponse promocion = promocionService.findCupon(cupon);
        return Response.setResponse(promocion, HttpStatus.OK);

    }
}
