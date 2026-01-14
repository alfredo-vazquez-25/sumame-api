package ar.org.sumame.api.domain.repository;

import ar.org.sumame.api.domain.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);
}