package co.edu.comfanorte.splitter.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.comfanorte.splitter.model.entity.CursoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioCursoEntity;
import co.edu.comfanorte.splitter.service.implementation.CursoService;

@RestController
@RequestMapping("/curso")
@Validated
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/usuario/{usuarioId}")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<Object> getCursosByUsuarioId(@PathVariable Integer usuarioId) {
        try {
            List<UsuarioCursoEntity> cursos = cursoService.getCursosByUsuarioId(usuarioId);
            return ResponseEntity.ok().body(cursos);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("type", "error");
            errorResponse.put("msg", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
