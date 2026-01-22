package ar.org.sumame.api.security;

import ar.org.sumame.api.domain.entity.Rol;
import ar.org.sumame.api.domain.entity.Usuario;
import ar.org.sumame.api.domain.enums.RolUsuario;
import ar.org.sumame.api.domain.repository.RolRepository;
import ar.org.sumame.api.domain.repository.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        Rol rol = rolRepository.findByCodigo(RolUsuario.RECLUTADOR)
                .orElseThrow(() -> new IllegalStateException("Rol RECLUTADOR no existe"));

        Usuario usuario = new Usuario("reclutador@test.com", true, rol);
        usuario.setPassword(passwordEncoder.encode("1234"));

        usuarioRepository.save(usuario);
    }

    @Test
    void login_devuelveToken() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "email": "reclutador@test.com",
                          "password": "1234"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }
}
