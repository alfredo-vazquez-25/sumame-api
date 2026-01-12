package ar.org.sumame.api.domain.repository;

import ar.org.sumame.api.domain.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findByUsuarioId(Long usuarioId);

    boolean existsByCuit(String cuit);
}
