package com.utp.casa_europa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.utp.casa_europa.utils.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Response<String>> handlerUserAlreadyExistException(UserAlreadyExistException ex){
        return Response.setResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    //Login Exceptions
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response<String>> handlerBadCredentialsException(BadCredentialsException ex){
        return Response.setResponse("Credenciales invalidas", HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response<String>> handlerUserNotFoundException(UserNotFoundException ex){
        return Response.setResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    //Entidades no encontradas
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response<String>> handlerEntityNotFoundException(EntityNotFoundException ex){
        return Response.setResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
