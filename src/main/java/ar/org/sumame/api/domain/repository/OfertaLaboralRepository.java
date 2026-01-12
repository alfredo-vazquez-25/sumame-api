package ar.org.sumame.api.domain.repository;

import ar.org.sumame.api.domain.entity.OfertaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfertaLaboralRepository extends JpaRepository<OfertaLaboral, Long> {

    List<OfertaLaboral> findByActivaTrue();

    List<OfertaLaboral> findByEmpresaId(Long empresaId);
}
