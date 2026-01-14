package ar.org.sumame.api.domain.repository;

import ar.org.sumame.api.domain.entity.Postulante;

import java.util.Optional;

public interface PostulanteRepository {

    Postulante save(Postulante postulante);

    Optional<Postulante> findById(Long id);

    Optional<Postulante> findByUsuarioId(Long usuarioId);
}
