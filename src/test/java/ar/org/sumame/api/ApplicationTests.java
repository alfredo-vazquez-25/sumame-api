package ar.org.sumame.api;

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
class ApplicationTests {

	@Test
	void contextLoads() {
	}
}