package co.edu.comfanorte.splitter.controller;

import co.edu.comfanorte.splitter.model.dto.*;
import co.edu.comfanorte.splitter.model.entity.ResultadoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioEntity;
import co.edu.comfanorte.splitter.service.interfaces.ResultadoInterface;
import co.edu.comfanorte.splitter.util.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    @PreAuthorize("hasAnyAuthority('ROL_PROFESOR', 'ROL_ESTUDIANTE')")
    public ResponseEntity<ResponseDTO> guardarResultado(@Valid @RequestBody ResultadoDTO resultadoDTO) {
        try {
            service.registerResult(resultadoDTO);
            return ResponseEntity.ok().body(new ResponseDTO("success", "Resultado registrado con exito."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO("error", e.getMessage()));
        }
    }

    @GetMapping("/results")
    @PreAuthorize("hasAnyAuthority('ROL_PROFESOR', 'ROL_ESTUDIANTE')")
    public ResponseEntity<List<ResultadosDTO>> detalleUsuario(@RequestParam(name="idTema") int idTema, @RequestParam(name="idUser") int idUser) {
        try {
            List<ResultadosDTO> results = service.getResults(idTema, idUser)
                    .stream().map(ObjectMapper::fromEntityToDto).collect(Collectors.toList());
            return ResponseEntity.ok().body(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/results/user/{idUser}")
    @PreAuthorize("hasAnyAuthority('ROL_PROFESOR', 'ROL_ESTUDIANTE')")
    public ResponseEntity<List<ResultadosDTO>> getResultsByUser(@PathVariable("idUser") Integer idUser) {
        try {
            List<ResultadosDTO> results = service.getResultsByUser(idUser)
                    .stream().map(ObjectMapper::fromEntityToDto).collect(Collectors.toList());
            return ResponseEntity.ok().body(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
