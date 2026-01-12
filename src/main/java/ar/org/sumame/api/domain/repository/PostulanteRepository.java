package ar.org.sumame.api.domain.repository;

import ar.org.sumame.api.domain.entity.Postulante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostulanteRepository extends JpaRepository<Postulante, Long> {

    Optional<Postulante> findByUsuarioId(Long usuarioId);
}