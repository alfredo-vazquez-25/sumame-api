package ar.org.sumame.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ar.org.sumame.api.infrastructure.persistence.jpa")
@EntityScan(basePackages = "ar.org.sumame.api.domain.entity")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
