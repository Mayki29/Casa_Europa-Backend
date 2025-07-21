package com.utp.casa_europa.utils;

public class TokenValidation {

    public static String extractToken(String authHeader){
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remueve "Bearer "
            return token;
        }
        return null;
    }
}
