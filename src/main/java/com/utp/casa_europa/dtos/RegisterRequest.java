package com.utp.casa_europa.dtos;

public record RegisterRequest(
    String email,
    String password,
    String nombre,
    String apellido,
    String telefono
) {

}
