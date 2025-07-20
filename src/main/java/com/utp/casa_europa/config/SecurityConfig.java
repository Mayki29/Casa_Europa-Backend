package com.utp.casa_europa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.utp.casa_europa.models.Token;
import com.utp.casa_europa.repositories.ITokenRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    
    @Autowired
    private AuthenticationProvider authenticationProvider;
    
    @Autowired
    private ITokenRepository tokenRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf( csrf -> csrf.disable())
            .authorizeHttpRequests(req ->
                    req
                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/productos/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/productos/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/productos/**")
                        .hasRole("ADMIN")
                        .requestMatchers("/api/venta").authenticated()
                        .requestMatchers( "/api/auth/**")
                        .permitAll()
                        .anyRequest()
                        .permitAll()
                        //.authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .logout(logout ->
                logout.logoutUrl("/auth/logout")
                    .addLogoutHandler((request, response, authentication) -> {
                        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                        logout(authHeader);
                    })
                    .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
            );


        return http.build();
    }

    private void logout(String token){
        if(token == null || !token.startsWith("Bearer ")){
            throw new IllegalArgumentException("Invalid token");
        }

        String jwtToken = token.substring(7);
        Token foundToken = tokenRepository.findByToken(jwtToken)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));
        foundToken.setExpired(true);        
        foundToken.setRevoked(true);        
        tokenRepository.save(foundToken);
        
    }
}
