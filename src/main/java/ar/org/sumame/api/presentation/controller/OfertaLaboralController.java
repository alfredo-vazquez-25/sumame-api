package ar.org.sumame.api.presentation.controller;

import ar.org.sumame.api.application.dto.oferta.OfertaLaboralCreateRequest;
import ar.org.sumame.api.application.dto.oferta.OfertaLaboralResponse;
import ar.org.sumame.api.application.service.OfertaLaboralService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaLaboralController {

    private final OfertaLaboralService ofertaLaboralService;

    public OfertaLaboralController(OfertaLaboralService ofertaLaboralService) {
        this.ofertaLaboralService = ofertaLaboralService;
    }

    @PostMapping
    public ResponseEntity<OfertaLaboralResponse> crear(
            @RequestParam Long usuarioId,
            @Valid @RequestBody OfertaLaboralCreateRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ofertaLaboralService.crearOferta(usuarioId, request));
    }

    @GetMapping
    public ResponseEntity<List<OfertaLaboralResponse>> listar() {
        return ResponseEntity.ok(ofertaLaboralService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertaLaboralResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ofertaLaboralService.buscarPorId(id));
    }
}
