package co.edu.comfanorte.splitter.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.comfanorte.splitter.model.dto.ResponseDTO;
import co.edu.comfanorte.splitter.model.entity.CursoEntity;
import co.edu.comfanorte.splitter.service.implementation.CursoService;

@RestController
@RequestMapping("/splitter/v1/cursos")
@Validated
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/{usuarioId}")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<ResponseDTO> getCursosByUsuarioId(@PathVariable Integer usuarioId) {
        try {
            List<CursoEntity> cursos = cursoService.getCursosByUsuarioId(usuarioId);
            return ResponseEntity.ok().body(new ResponseDTO("success", cursos));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO("error", e.getMessage()));
        }
        

    }
}
