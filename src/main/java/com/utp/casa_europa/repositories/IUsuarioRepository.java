package com.utp.casa_europa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.utp.casa_europa.models.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

}
