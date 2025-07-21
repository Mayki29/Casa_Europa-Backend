package com.utp.casa_europa.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.casa_europa.models.Token;
import com.utp.casa_europa.models.Usuario;
import com.utp.casa_europa.repositories.ITokenRepository;
import com.utp.casa_europa.repositories.IUsuarioRepository;
import com.utp.casa_europa.services.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ITokenRepository tokenRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @org.springframework.lang.NonNull HttpServletRequest request,
            @org.springframework.lang.NonNull HttpServletResponse response,
            @org.springframework.lang.NonNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println("1: "+request.getHeader(HttpHeaders.AUTHORIZATION));

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getServletPath().contains("/auth")) {
            System.out.println(request);
            filterChain.doFilter(request, response);
            return;
        }
        if (!isProtectedPath(request)) {
            System.out.println("2: "+request);
            filterChain.doFilter(request, response);
            return;
        }



        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = authHeader.substring(7);
        String userEmail = null;
        try {
            userEmail = jwtService.extractUsername(jwtToken);
        } catch (ExpiredJwtException e) {


            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", false);
            responseMap.put("error", "Token expirado");
            responseMap.put("statusCode", HttpServletResponse.SC_UNAUTHORIZED);
            responseMap.put("timestamp", System.currentTimeMillis());

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(responseMap));
            return;
        }

        if (userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        Token token = tokenRepository.findByToken(jwtToken)
                .orElse(null);
        if (token == null || token.isExpired() || token.isRevoked()) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        Optional<Usuario> usuario = usuarioRepository.findByEmail(userDetails.getUsername());
        if (usuario.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean isTokenValid = jwtService.isTokenValid(jwtToken, usuario.get());
        if (!isTokenValid) {
            return;
        }

        var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }

    private boolean isProtectedPath(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod();
        System.out.println(path);
        System.out.println(method);
        // Proteger solo cuando es POST, PUT o DELETE en /api/productos/**
        if (path.startsWith("/api/productos")) {
            return method.equals("POST") || method.equals("PUT") || method.equals("DELETE");
        }

        // Proteger POST a /api/venta
        if (path.equals("/api/venta") && method.equals("POST")) {
            return true;
        }

        return false;
    }

}
