package ar.org.sumame.api.application.impl;

import ar.org.sumame.api.application.dto.oferta.OfertaLaboralCreateRequest;
import ar.org.sumame.api.application.dto.oferta.OfertaLaboralResponse;
import ar.org.sumame.api.application.mapper.OfertaLaboralMapper;
import ar.org.sumame.api.application.service.OfertaLaboralService;
import ar.org.sumame.api.domain.entity.Empresa;
import ar.org.sumame.api.domain.entity.OfertaLaboral;
import ar.org.sumame.api.domain.enums.RolUsuario;
import ar.org.sumame.api.domain.repository.EmpresaRepository;
import ar.org.sumame.api.domain.repository.OfertaLaboralRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfertaLaboralServiceImpl implements OfertaLaboralService {

    private final OfertaLaboralRepository ofertaRepository;
    private final EmpresaRepository empresaRepository;
    private final OfertaLaboralMapper mapper;

    public OfertaLaboralServiceImpl(
            OfertaLaboralRepository ofertaRepository,
            EmpresaRepository empresaRepository,
            OfertaLaboralMapper mapper) {

        this.ofertaRepository = ofertaRepository;
        this.empresaRepository = empresaRepository;
        this.mapper = mapper;
    }

    @Override
    public OfertaLaboralResponse crearOferta(Long usuarioId, OfertaLaboralCreateRequest request) {

        Empresa empresa = empresaRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        if (!empresa.getUsuario().getRol().getNombre().equals(RolUsuario.EMPRESA)) {
            throw new RuntimeException("El usuario no tiene rol EMPRESA");
        }

        OfertaLaboral oferta = mapper.toEntity(request);
        oferta.setEmpresa(empresa);

        OfertaLaboral guardada = ofertaRepository.save(oferta);
        return mapper.toResponse(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfertaLaboralResponse> listarTodas() {
        return ofertaRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OfertaLaboralResponse buscarPorId(Long id) {

        OfertaLaboral oferta = ofertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        return mapper.toResponse(oferta);
    }
}
