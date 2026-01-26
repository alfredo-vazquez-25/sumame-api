package ar.org.sumame.api.auth;

import ar.org.sumame.api.config.TestSecurityBeansConfig;
import ar.org.sumame.api.domain.entity.Rol;
import ar.org.sumame.api.domain.entity.Usuario;
import ar.org.sumame.api.domain.enums.RolUsuario;
import ar.org.sumame.api.domain.repository.RolRepository;
import ar.org.sumame.api.domain.repository.UsuarioRepository;
import ar.org.sumame.api.infrastructure.persistence.jpa.SpringRolJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@ActiveProfiles("test")
@Import(TestSecurityBeansConfig.class)
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SpringRolJpaRepository springRolJpaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        usuarioRepository.deleteAll();
        springRolJpaRepository.deleteAll();

        Rol rol = springRolJpaRepository.findByNombre(RolUsuario.RECLUTADOR)
                .orElseGet(() -> springRolJpaRepository.save(
                        new Rol(RolUsuario.RECLUTADOR)
                ));

        Usuario usuario = new Usuario("reclutador@test.com", true, rol);
        usuario.setPassword(passwordEncoder.encode("1234"));

        usuarioRepository.save(usuario);
    }


    @Test
    void login_ok_devuelveToken() throws Exception {
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

    @Test
    void login_passwordIncorrecta_devuelve401() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "email": "reclutador@test.com",
                      "password": "wrong"
                    }
                    """))
                .andExpect(status().isForbidden());
    }
}
