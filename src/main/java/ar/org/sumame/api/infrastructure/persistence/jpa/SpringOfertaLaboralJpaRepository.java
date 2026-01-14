package ar.org.sumame.api.infrastructure.persistence.jpa;

import ar.org.sumame.api.domain.entity.OfertaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringOfertaLaboralJpaRepository
        extends JpaRepository<OfertaLaboral, Long> {
}