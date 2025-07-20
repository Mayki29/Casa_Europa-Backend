package com.utp.casa_europa.controllers;

import com.utp.casa_europa.dtos.VentaPagoDto;
import com.utp.casa_europa.dtos.VentaRequest;
import com.utp.casa_europa.dtos.payment.ResponsePaymentDto;
import com.utp.casa_europa.models.Venta;
import com.utp.casa_europa.services.JwtService;
import com.utp.casa_europa.services.VentaService;
import com.utp.casa_europa.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<Response<ResponsePaymentDto>> saveVenta(@RequestBody VentaPagoDto ventaRequest, @RequestHeader("Authorization") String header){
        String jwtToken = header.substring(7);
        Long userId = jwtService.extractId(jwtToken);
        return Response.setResponse(ventaService.saveVenta(ventaRequest, userId), HttpStatus.ACCEPTED);
    }

}
