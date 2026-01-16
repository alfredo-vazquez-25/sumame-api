package ar.org.sumame.api.security.util;

import ar.org.sumame.api.application.exception.ForbiddenException;
import ar.org.sumame.api.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
        // utility class
    }

    public static CustomUserDetails getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !(auth.getPrincipal() instanceof CustomUserDetails)) {
            throw new ForbiddenException("Usuario no autenticado");
        }

        return (CustomUserDetails) auth.getPrincipal();
    }
}