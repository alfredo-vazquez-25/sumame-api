package ar.org.sumame.api;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
		"spring.autoconfigure.exclude=" +
				"org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration," +
				"org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration," +
				"org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration"
})
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void crearOferta_sinUsuario_devuelve401() throws Exception {

		mockMvc.perform(post("/api/ofertas")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
                            {
                              "titulo": "Backend Java",
                              "descripcion": "Spring Boot"
                            }
                        """))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void login_devuelveToken() throws Exception {

		mockMvc.perform(post("/api/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
                            {
                              "username": "reclutador@test.com",
                              "password": "1234"
                            }
                        """))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token").exists());
	}

	@Test
	void crearOferta_conTokenJWT_devuelve200() throws Exception {

		String token = obtenerToken();

		mockMvc.perform(post("/api/ofertas")
						.header("Authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
                            {
                              "titulo": "Backend Java",
                              "descripcion": "Spring Boot"
                            }
                        """))
				.andExpect(status().isOk());
	}

	private String obtenerToken() throws Exception {

		String response = mockMvc.perform(post("/api/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
                            {
                              "username": "reclutador@test.com",
                              "password": "1234"
                            }
                        """))
				.andReturn()
				.getResponse()
				.getContentAsString();

		return JsonPath.read(response, "$.token");
	}
}
