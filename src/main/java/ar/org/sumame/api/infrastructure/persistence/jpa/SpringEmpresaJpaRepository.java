package ar.org.sumame.api.infrastructure.persistence.jpa;

import ar.org.sumame.api.domain.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringEmpresaJpaRepository
        extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findByUsuarioId(Long usuarioId);
}