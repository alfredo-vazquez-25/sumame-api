package ar.org.sumame.api.security.util;

import ar.org.sumame.api.application.exception.ForbiddenException;
import ar.org.sumame.api.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static CustomUserDetails getCurrentUser() {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new ForbiddenException("Usuario no autenticado");
        }

        if (!(auth.getPrincipal() instanceof CustomUserDetails user)) {
            throw new ForbiddenException("Principal inválido");
        }

        return user;
    }
}
