package ar.org.sumame.api.presentation.controller;

import ar.org.sumame.api.application.service.OfertaLaboralService;
import ar.org.sumame.api.domain.entity.OfertaLaboral;
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
    public ResponseEntity<OfertaLaboral> crear(@RequestBody OfertaLaboral oferta) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ofertaLaboralService.crearOferta(oferta));
    }

    @GetMapping
    public ResponseEntity<List<OfertaLaboral>> listar() {
        return ResponseEntity.ok(ofertaLaboralService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertaLaboral> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ofertaLaboralService.buscarPorId(id));
    }
}
