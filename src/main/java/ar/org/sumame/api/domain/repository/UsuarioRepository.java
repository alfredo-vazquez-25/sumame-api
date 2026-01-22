package ar.org.sumame.api.domain.repository;

import ar.org.sumame.api.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
}