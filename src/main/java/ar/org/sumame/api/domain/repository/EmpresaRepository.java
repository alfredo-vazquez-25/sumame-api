package ar.org.sumame.api.domain.repository;

import ar.org.sumame.api.domain.entity.Empresa;

import java.util.Optional;

public interface EmpresaRepository {

    Empresa save(Empresa empresa);

    Optional<Empresa> findById(Long id);

    Optional<Empresa> findByUsuarioId(Long usuarioId);
}
