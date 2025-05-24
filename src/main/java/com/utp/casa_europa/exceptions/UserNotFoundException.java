package com.utp.casa_europa.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("Usuario no encontrado");
    }
}
