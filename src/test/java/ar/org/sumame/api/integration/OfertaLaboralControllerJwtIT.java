package ar.org.sumame.api.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OfertaLaboralControllerJwtIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String tokenReclutador;
    private String tokenPostulante;

    @BeforeEach
    void login() throws Exception {
        tokenReclutador = obtenerTokenJwt("reclutador@test.com", "1234");
        tokenPostulante = obtenerTokenJwt("postulante@test.com", "1234");
    }

    @Test
    void crearOferta_conJwtValido_devuelve201() throws Exception {

        mockMvc.perform(
                        post("/api/ofertas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenReclutador)
                                .content("""
                                    {
                                      "titulo": "Backend Java",
                                      "descripcion": "Spring Boot + JWT",
                                      "ubicacion": "Remoto"
                                    }
                                """)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void crearOferta_sinJwt_devuelve401() throws Exception {

        mockMvc.perform(
                        post("/api/ofertas")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void crearOferta_conRolIncorrecto_devuelve403() throws Exception {

        mockMvc.perform(
                        post("/api/ofertas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenPostulante)
                                .content("{}")
                )
                .andExpect(status().isForbidden());
    }

    private String obtenerTokenJwt(String email, String password) throws Exception {

        MvcResult result = mockMvc.perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        Map.of(
                                                "email", email,
                                                "password", password
                                        )
                                ))
                )
                .andExpect(status().isOk())
                .andReturn();

        JsonNode json = objectMapper.readTree(
                result.getResponse().getContentAsString()
        );

        return json.get("token").asText();
    }
}
