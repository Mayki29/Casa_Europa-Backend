package com.utp.casa_europa.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.utp.casa_europa.utils.Response;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handlerUserAlreadyExistException(UserAlreadyExistException ex){
        return Response.setResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    //Login Exceptions
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handlerBadCredentialsException(BadCredentialsException ex){
        return Response.setResponse("Credenciales invalidas", HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerUserNotFoundException(UserNotFoundException ex){
        return Response.setResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
