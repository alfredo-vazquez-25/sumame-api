package ar.org.sumame.api.infrastructure.persistence.repository;

import ar.org.sumame.api.domain.entity.OfertaLaboral;
import ar.org.sumame.api.domain.entity.Postulacion;
import ar.org.sumame.api.domain.entity.Postulante;
import ar.org.sumame.api.domain.repository.PostulacionRepository;
import ar.org.sumame.api.infrastructure.persistence.jpa.SpringPostulacionJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostulacionRepositoryJpa implements PostulacionRepository {

    private final SpringPostulacionJpaRepository jpa;

    public PostulacionRepositoryJpa(SpringPostulacionJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public boolean existsByPostulanteAndOferta(Postulante p, OfertaLaboral o) {
        return jpa.existsByPostulanteAndOferta(p, o);
    }

    @Override
    public List<Postulacion> findByPostulante(Postulante p) {
        return jpa.findByPostulante(p);
    }

    @Override
    public Postulacion save(Postulacion postulacion) {
        return jpa.save(postulacion);
    }
}