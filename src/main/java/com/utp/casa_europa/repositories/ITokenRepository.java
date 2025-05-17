package com.utp.casa_europa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.utp.casa_europa.models.Token;

public interface ITokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}
