package co.edu.comfanorte.splitter.controller;

import co.edu.comfanorte.splitter.model.dto.HistoricoDTO;
import co.edu.comfanorte.splitter.model.dto.ResponseDTO;
import co.edu.comfanorte.splitter.model.entity.CursoEntity;
import co.edu.comfanorte.splitter.model.entity.HistoricoEntity;
import co.edu.comfanorte.splitter.service.implementation.HistoricoService;
import co.edu.comfanorte.splitter.service.interfaces.HistoricoInterface;
import co.edu.comfanorte.splitter.util.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/splitter/v1/historico")
@Validated
public class HistoricoController {

    @Autowired
    private  HistoricoInterface service;
    @GetMapping("/generar")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<ResponseDTO> getAveragePerYear() {
        try {
           List<HistoricoEntity> historic= service.generateHistorico();
           return ResponseEntity.ok(ResponseDTO.builder().msg("Reporte del presente año creado con exito").type("OK").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO("error", e.getMessage()));
        }


    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<List<HistoricoDTO>> getHistorics() {
        try {
            List<HistoricoDTO> historic= service.getAll().stream().map(ObjectMapper::fromEntityToDto).collect(Collectors.toList());
            return ResponseEntity.ok(historic);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }


    }
}
