package ar.org.sumame.api.infrastructure.persistence.repository;

import ar.org.sumame.api.domain.entity.Postulante;
import ar.org.sumame.api.domain.repository.PostulanteRepository;
import ar.org.sumame.api.infrastructure.persistence.jpa.SpringPostulanteJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostulanteRepositoryJpa implements PostulanteRepository {

    private final SpringPostulanteJpaRepository jpaRepository;

    public PostulanteRepositoryJpa(SpringPostulanteJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Postulante save(Postulante postulante) {
        return jpaRepository.save(postulante);
    }

    @Override
    public Optional<Postulante> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Postulante> findByUsuarioId(Long usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId);
    }
}