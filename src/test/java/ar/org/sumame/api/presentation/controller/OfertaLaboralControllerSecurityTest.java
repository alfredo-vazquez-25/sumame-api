package ar.org.sumame.api.presentation.controller;

import ar.org.sumame.api.application.dto.oferta.OfertaLaboralResponse;
import ar.org.sumame.api.application.service.OfertaLaboralService;
import ar.org.sumame.api.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = OfertaLaboralController.class,
        excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration.class
        }
)
@Import(TestSecurityConfig.class)
@ActiveProfiles("test")
class OfertaLaboralControllerSecurityTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OfertaLaboralService ofertaLaboralService;

    @Test
    @WithMockUser(roles = "RECLUTADOR")
    void crearOferta_conRolReclutador_devuelve200() throws Exception {

        when(ofertaLaboralService.crearOferta(any()))
                .thenReturn(new OfertaLaboralResponse());

        mockMvc.perform(post("/api/ofertas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "titulo": "Backend Java",
                              "descripcion": "Spring Boot"
                            }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "POSTULANTE")
    void crearOferta_conRolIncorrecto_devuelve403() throws Exception {

        mockMvc.perform(post("/api/ofertas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void crearOferta_sinUsuario_devuelve401() throws Exception {
        mockMvc.perform(post("/api/ofertas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }
}

