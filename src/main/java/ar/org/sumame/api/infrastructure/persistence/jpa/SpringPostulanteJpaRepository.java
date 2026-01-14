package ar.org.sumame.api.infrastructure.persistence.jpa;

import ar.org.sumame.api.domain.entity.Postulante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringPostulanteJpaRepository
        extends JpaRepository<Postulante, Long> {

    Optional<Postulante> findByUsuarioId(Long usuarioId);
}
