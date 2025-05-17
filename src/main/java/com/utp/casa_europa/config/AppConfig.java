package com.utp.casa_europa.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.utp.casa_europa.models.Usuario;
import com.utp.casa_europa.repositories.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Autowired
    private IUsuarioRepository usuarioRepository;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }


    @Bean
    public UserDetailsService userDetailsService(){ //Esta sera la forma en la que busca el usuario
        return email -> { 
            Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return User.builder() //Es una clase interna
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(usuario.getRol().name())))
                .build();
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());//De que forma va buscar el usuario
        authProvider.setPasswordEncoder(passwordEncoder());//de que forma esta encriptada la contraseña
        return authProvider;
    }

}
