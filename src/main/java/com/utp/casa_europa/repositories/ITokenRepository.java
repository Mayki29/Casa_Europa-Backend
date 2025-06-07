package com.utp.casa_europa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.utp.casa_europa.models.Token;

@Repository
public interface ITokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}
