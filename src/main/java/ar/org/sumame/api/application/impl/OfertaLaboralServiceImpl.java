package ar.org.sumame.api.application.impl;

import ar.org.sumame.api.application.dto.oferta.OfertaLaboralCreateRequest;
import ar.org.sumame.api.application.dto.oferta.OfertaLaboralResponse;
import ar.org.sumame.api.application.exception.ForbiddenException;
import ar.org.sumame.api.application.mapper.OfertaLaboralMapper;
import ar.org.sumame.api.application.service.OfertaLaboralService;
import ar.org.sumame.api.domain.entity.Empresa;
import ar.org.sumame.api.domain.entity.OfertaLaboral;
import ar.org.sumame.api.domain.entity.Usuario;
import ar.org.sumame.api.domain.enums.RolUsuario;
import ar.org.sumame.api.domain.repository.EmpresaRepository;
import ar.org.sumame.api.domain.repository.OfertaLaboralRepository;
import ar.org.sumame.api.security.CustomUserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
@Transactional
public class OfertaLaboralServiceImpl implements OfertaLaboralService {

    private final OfertaLaboralRepository ofertaRepository;
    private final EmpresaRepository empresaRepository;
    private final OfertaLaboralMapper mapper;
    private static final Logger log =
            LoggerFactory.getLogger(OfertaLaboralServiceImpl.class);


    public OfertaLaboralServiceImpl(
            OfertaLaboralRepository ofertaRepository,
            EmpresaRepository empresaRepository,
            OfertaLaboralMapper mapper) {

        this.ofertaRepository = ofertaRepository;
        this.empresaRepository = empresaRepository;
        this.mapper = mapper;
    }

    @Override
    public OfertaLaboralResponse crearOferta(OfertaLaboralCreateRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("AUTH = " + auth);
        System.out.println("AUTHORITIES = " + auth.getAuthorities());

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Usuario usuario = userDetails.getUsuario();

        log.info("Usuario autenticado ID: {}", usuario.getId());
        log.info("Usuario email: {}", usuario.getEmail());
        log.info("Usuario rol: {}", usuario.getRol().getNombre());

        Empresa empresa = empresaRepository.findByUsuarioId(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        OfertaLaboral oferta = mapper.toEntity(request);
        oferta.setEmpresa(empresa);

        return mapper.toResponse(ofertaRepository.save(oferta));
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
        return mapper.toResponse(
                ofertaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Oferta no encontrada"))
        );
    }
}
