package ar.org.sumame.api.presentation.controller;

import ar.org.sumame.api.application.dto.oferta.OfertaLaboralCreateRequest;
import ar.org.sumame.api.application.dto.oferta.OfertaLaboralResponse;
import ar.org.sumame.api.application.exception.ForbiddenException;
import ar.org.sumame.api.application.service.OfertaLaboralService;
import ar.org.sumame.api.domain.enums.RolUsuario;
import ar.org.sumame.api.security.CustomUserDetails;
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
    @PostMapping
    public OfertaLaboralResponse crearOferta(
            @Valid @RequestBody OfertaLaboralCreateRequest request
    ) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ForbiddenException("Usuario no autenticado");
        }

        // DEV: principal es String
        // PROD: principal es CustomUserDetails
        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {

            var rol = userDetails.getUsuario().getRol().getNombre();

            if (rol != RolUsuario.RECLUTADOR) {
                throw new ForbiddenException("El usuario no tiene rol RECLUTADOR");
            }
        }

        return ofertaLaboralService.crearOferta(request);
    }
}
