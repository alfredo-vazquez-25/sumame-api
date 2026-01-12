package ar.org.sumame.api.presentation.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @Operation(summary = "Estado del servicio", description = "Verifica si la API de Sumame está activa")
    @GetMapping
    public String health() {
        return "Sumame API activa";
    }
}
