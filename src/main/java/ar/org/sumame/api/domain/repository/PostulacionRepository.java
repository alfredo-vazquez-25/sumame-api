package ar.org.sumame.api.domain.repository;

import ar.org.sumame.api.domain.entity.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {

    boolean existsByPostulanteIdAndOfertaLaboralId(Long postulanteId, Long ofertaLaboralId);

    List<Postulacion> findByPostulanteId(Long postulanteId);
}
