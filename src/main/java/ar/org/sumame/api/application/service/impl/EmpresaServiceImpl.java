package ar.org.sumame.api.application.service.impl;

import ar.org.sumame.api.application.service.EmpresaService;
import ar.org.sumame.api.domain.entity.Empresa;
import ar.org.sumame.api.domain.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public Empresa crear(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa obtenerPorUsuario(Long usuarioId) {
        return empresaRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }
}
