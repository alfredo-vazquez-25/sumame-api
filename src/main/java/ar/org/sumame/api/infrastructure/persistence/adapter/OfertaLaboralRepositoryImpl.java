package ar.org.sumame.api.infrastructure.persistence.adapter;

import ar.org.sumame.api.domain.entity.OfertaLaboral;
import ar.org.sumame.api.domain.repository.OfertaLaboralRepository;
import ar.org.sumame.api.infrastructure.persistence.jpa.SpringOfertaLaboralJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OfertaLaboralRepositoryImpl implements OfertaLaboralRepository {

    private final SpringOfertaLaboralJpaRepository jpaRepository;

    public OfertaLaboralRepositoryImpl(SpringOfertaLaboralJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public OfertaLaboral save(OfertaLaboral oferta) {
        return jpaRepository.save(oferta);
    }

    @Override
    public Optional<OfertaLaboral> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<OfertaLaboral> findAll() {
        return jpaRepository.findAll();
    }
}