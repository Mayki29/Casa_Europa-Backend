package com.utp.casa_europa.controllers;

public record RegisterRequest(
    String email,
    String password,
    String nombre
) {

}
