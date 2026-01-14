package ar.org.sumame.api.domain.repository;

import ar.org.sumame.api.domain.entity.OfertaLaboral;

import java.util.List;
import java.util.Optional;

public interface OfertaLaboralRepository {
    OfertaLaboral save(OfertaLaboral oferta);
    Optional<OfertaLaboral> findById(Long id);
    List<OfertaLaboral> findAll();
}
