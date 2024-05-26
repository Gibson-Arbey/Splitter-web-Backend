package co.edu.comfanorte.splitter.controller;

import co.edu.comfanorte.splitter.model.dto.GetResutsDTO;
import co.edu.comfanorte.splitter.model.dto.ResultadosDTO;
import co.edu.comfanorte.splitter.model.dto.TemaDTO;
import co.edu.comfanorte.splitter.service.interfaces.TemaInterface;
import co.edu.comfanorte.splitter.util.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/splitter/v1/temas")
@Validated
public class TemaController {

    @Autowired
    private TemaInterface service;

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROL_PROFESOR', 'ROL_ESTUDIANTE')")
    public ResponseEntity<List<TemaDTO>> detalleUsuario() {
        try {
            List<TemaDTO> results = service.getTemas()
                    .stream().map(ObjectMapper::fromEntityToDto).collect(Collectors.toList());
            return ResponseEntity.ok().body(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
