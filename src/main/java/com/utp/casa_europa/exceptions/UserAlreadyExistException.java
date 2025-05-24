package com.utp.casa_europa.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email){
        super("Usuario con email '"+ email +"' ya existe" );
    }
}
