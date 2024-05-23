package co.edu.comfanorte.splitter.controller;

import co.edu.comfanorte.splitter.model.dto.*;
import co.edu.comfanorte.splitter.model.entity.ResultadoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioEntity;
import co.edu.comfanorte.splitter.service.interfaces.ResultadoInterface;
import co.edu.comfanorte.splitter.util.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/splitter/v1/resultados")
@Validated
public class ResultadoController {

    @Autowired
    private ResultadoInterface service;

    @PostMapping("/guardarResultado")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<ResponseDTO> guardarResultado(@Valid @RequestBody ResultadoDTO resultadoDTO) {
        try {
            service.registerResult(resultadoDTO);
            return ResponseEntity.ok().body(new ResponseDTO("success", "Resultado registrado con exito."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO("error", e.getMessage()));
        }
    }

    @GetMapping("/results")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<List<ResultadosDTO>> detalleUsuario(@RequestBody @Valid GetResutsDTO getResutsDTO) {
        try {
            List<ResultadosDTO> results = service.getResults(getResutsDTO.getTema(), getResutsDTO.getUserName())
                    .stream().map(ObjectMapper::fromEntityToDto).collect(Collectors.toList());
            return ResponseEntity.ok().body(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
