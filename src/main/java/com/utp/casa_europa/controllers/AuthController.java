package com.utp.casa_europa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.casa_europa.dtos.LoginRequest;
import com.utp.casa_europa.dtos.RegisterRequest;
import com.utp.casa_europa.dtos.TokenResponse;
import com.utp.casa_europa.services.AuthService;
import com.utp.casa_europa.utils.Response;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response<TokenResponse>> register(@RequestBody RegisterRequest request){
        var token = authService.register(request);
        return Response.setResponse(token, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<Response<TokenResponse>> authenticate(@RequestBody LoginRequest request){
       var token = authService.login(request);
       return Response.setResponse(token, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Response<TokenResponse>> authenticate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        TokenResponse token = authService.refreshToken(authHeader);
        return Response.setResponse(token, HttpStatus.OK);
    }
}
