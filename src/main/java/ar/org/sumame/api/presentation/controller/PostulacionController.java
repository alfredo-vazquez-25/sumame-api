package ar.org.sumame.api.presentation.controller;

import ar.org.sumame.api.application.service.PostulacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postulaciones")
public class PostulacionController {

    private final PostulacionService postulacionService;

    public PostulacionController(PostulacionService postulacionService) {
        this.postulacionService = postulacionService;
    }

    @PostMapping
    public ResponseEntity<Void> postular(
            @RequestParam Long ofertaId,
            @RequestParam Long usuarioId) {

        postulacionService.postular(ofertaId, usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/postulante/{usuarioId}")
    public ResponseEntity<?> listarPorPostulante(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(
                postulacionService.listarPorPostulante(usuarioId)
        );
    }
}
