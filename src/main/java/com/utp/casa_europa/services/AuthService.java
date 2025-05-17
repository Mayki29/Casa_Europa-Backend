package com.utp.casa_europa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.utp.casa_europa.controllers.LoginRequest;
import com.utp.casa_europa.controllers.RegisterRequest;
import com.utp.casa_europa.controllers.TokenResponse;
import com.utp.casa_europa.models.Rol;
import com.utp.casa_europa.models.Token;
import com.utp.casa_europa.models.Usuario;
import com.utp.casa_europa.repositories.ITokenRepository;
import com.utp.casa_europa.repositories.IUsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ITokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenResponse register(RegisterRequest request){
        var user = Usuario.builder()
                    .nombre(request.nombre())
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .rol(Rol.ROLE_CLIENT)
                    .build();
        var savedUser = usuarioRepository.save(user);
        var jwtToken = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserToken(savedUser, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);         
    }

    public TokenResponse login(LoginRequest request){
        authenticationManager.authenticate( //se configura en app config
            new UsernamePasswordAuthenticationToken(
                request.email(), 
                request.password()
            )
        );
        var user = usuarioRepository.findByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        //revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
    }


    private void saveUserToken(Usuario usuario, String jwtToken){
        var token = Token.builder()
                    .usuario(usuario)
                    .token(jwtToken)
                    .tokenType(Token.TokenType.BEARER)
                    .expired(false)
                    .revoked(false)
                    .build();
        tokenRepository.save(token);
    }

    /*private void revokeAllUserTokens(Usuario usuario){
        List<Token> validUserTokens = tokenRepository.findAllValidIsFalseOrRevokedIsFalseByUserId(usuario.getId());
        if(!validUserTokens.isEmpty()){
            for(Token token : validUserTokens){
                token.setExpired(true);
                token.setRevoked(true);
            }
            tokenRepository.saveAll(validUserTokens);
        }
    }*/

    public TokenResponse refreshToken(String authHeader){
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new IllegalArgumentException("Invalid Bearer token");
        }

        String refreshToken = authHeader.substring(7);
        String userEmail = jwtService.extractUsername(refreshToken);

        if(userEmail == null){
            throw new IllegalArgumentException("Invalid Refresh Token");
        }
        Usuario usuario = usuarioRepository.findByEmail(userEmail)
                    .orElseThrow(()-> new UsernameNotFoundException(userEmail));
        if(!jwtService.isTokenValid(refreshToken, usuario)){
            throw new IllegalArgumentException("Invalid Refresh Token");
        }

        String accessToken = jwtService.generateToken(usuario);
        //revokeAllUserTokens(usuario);
        saveUserToken(usuario, accessToken);
        return new TokenResponse(accessToken, refreshToken);
    }
}
