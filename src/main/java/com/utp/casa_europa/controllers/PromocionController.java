package com.utp.casa_europa.controllers;

import com.utp.casa_europa.dtos.PromocionRequest;
import com.utp.casa_europa.dtos.PromocionResponse;
import com.utp.casa_europa.services.PromocionService;
import com.utp.casa_europa.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping()
    public ResponseEntity<Response<PromocionResponse>> createPromocion(@RequestBody PromocionRequest request){
        PromocionResponse promocion = promocionService.crearPromocion(request);
        return Response.setResponse(promocion, HttpStatus.CREATED);
    }

    @PatchMapping("/desactivar/{id}")
    public ResponseEntity<Response<PromocionResponse>> desactivarPromocion(@PathVariable Long id){
        PromocionResponse promocion = promocionService.desactivarPromocion(id);
        return Response.setResponse(promocion, HttpStatus.OK);
    }
    @PatchMapping("/activar/{id}")
    public ResponseEntity<Response<PromocionResponse>> activarPromocion(@PathVariable Long id){
        PromocionResponse promocion = promocionService.activarPromocion(id);
        return Response.setResponse(promocion, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Response<List<PromocionResponse>>> listarPromociones(){
        List<PromocionResponse> promociones = promocionService.listarPromociones();
        return Response.setResponse(promociones, HttpStatus.OK);
    }
}
