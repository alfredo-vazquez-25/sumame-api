package ar.org.sumame.api.security.dev;

import ar.org.sumame.api.domain.entity.Rol;
import ar.org.sumame.api.domain.entity.Usuario;
import ar.org.sumame.api.domain.enums.RolUsuario;
import ar.org.sumame.api.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Profile("dev")
public class DevAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        Rol rol = new Rol(RolUsuario.RECLUTADOR);

        Usuario usuario = new Usuario(
                "valfredo92@gmail.com",
                true,
                rol
        );

        CustomUserDetails userDetails = new CustomUserDetails(usuario);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}

