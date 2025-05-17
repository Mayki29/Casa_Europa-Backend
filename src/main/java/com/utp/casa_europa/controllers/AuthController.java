package com.utp.casa_europa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.casa_europa.services.AuthService;

@RestController
//@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest request){
        var token = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);


    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginRequest request){
       var token = authService.login(request);
       return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public TokenResponse authenticate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        return null;
    }

    // @GetMapping("/test")
    // public String test(){
    //     return "Hola mundo";
    // }

    @GetMapping("/test/auth")
    public String testAuth(){
        return "Hola mundo autenticado";
    }

    @GetMapping("/test/admin")
    public String testAuthAdmin(){
        return "Hola mundo admin autenticado";
    }
}
