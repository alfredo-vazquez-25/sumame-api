package ar.org.sumame.api.infrastructure.persistence.jpa;

import ar.org.sumame.api.domain.entity.OfertaLaboral;
import ar.org.sumame.api.domain.entity.Postulacion;
import ar.org.sumame.api.domain.entity.Postulante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringPostulacionJpaRepository
        extends JpaRepository<Postulacion, Long> {

    boolean existsByPostulanteAndOferta(Postulante postulante, OfertaLaboral oferta);

    List<Postulacion> findByPostulante(Postulante postulante);
}
