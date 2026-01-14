package ar.org.sumame.api.infrastructure.persistence.repository;

import ar.org.sumame.api.domain.entity.Empresa;
import ar.org.sumame.api.domain.repository.EmpresaRepository;
import ar.org.sumame.api.infrastructure.persistence.jpa.SpringEmpresaJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmpresaRepositoryJpa implements EmpresaRepository {

    private final SpringEmpresaJpaRepository jpaRepository;

    public EmpresaRepositoryJpa(SpringEmpresaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Empresa save(Empresa empresa) {
        return jpaRepository.save(empresa);
    }

    @Override
    public Optional<Empresa> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Empresa> findByUsuarioId(Long usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId);
    }
}