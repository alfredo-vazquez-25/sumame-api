package ar.org.sumame.api.infrastructure.persistence.jpa;

import ar.org.sumame.api.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringUsuarioJpaRepository
        extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
