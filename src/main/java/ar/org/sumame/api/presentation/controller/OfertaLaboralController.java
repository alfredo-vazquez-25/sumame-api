package ar.org.sumame.api.presentation.controller;

import ar.org.sumame.api.application.dto.oferta.OfertaLaboralCreateRequest;
import ar.org.sumame.api.application.dto.oferta.OfertaLaboralResponse;
import ar.org.sumame.api.application.exception.ForbiddenException;
import ar.org.sumame.api.application.service.OfertaLaboralService;
import ar.org.sumame.api.domain.enums.RolUsuario;
import ar.org.sumame.api.security.CustomUserDetails;
import ar.org.sumame.api.security.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaLaboralController {

    private final OfertaLaboralService ofertaLaboralService;

    public OfertaLaboralController(OfertaLaboralService ofertaLaboralService) {
        this.ofertaLaboralService = ofertaLaboralService;
    }

    @PreAuthorize("hasRole('RECLUTADOR')")
    @PostMapping("/api/ofertas")
    public OfertaLaboralResponse crearOferta(
            @RequestBody OfertaLaboralCreateRequest request
    ) {
        CustomUserDetails user = SecurityUtils.getCurrentUser();

        if (user.getUsuario().getRol().getNombre() != RolUsuario.RECLUTADOR) {
            throw new ForbiddenException("Rol inválido");
        }

        return ofertaLaboralService.crearOferta(request);
    }
}
