package co.edu.comfanorte.splitter.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.comfanorte.splitter.model.dto.ContrasenaDTO;
import co.edu.comfanorte.splitter.model.dto.UsuarioDTO;
import co.edu.comfanorte.splitter.model.entity.UsuarioEntity;
import co.edu.comfanorte.splitter.service.implementation.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@Validated
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/guardarEstudiante")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<String> guardarEstudiante(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            BeanUtils.copyProperties(usuarioDTO, usuarioEntity);
            usuarioEntity.setContrasena(passwordEncoder.encode(usuarioDTO.getContrasenaDesencriptada()));
            usuarioService.guardarUsuario(usuarioEntity, usuarioDTO.getCurso());
            String response = "Estudiante registrado con exito.";
            return ResponseEntity.ok().body("{\n \"type\": \"success\" \n \"msg\": \""+ response + "\"\n}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\n \"type\": \"error\" \n \"msg\": \""+ e.getMessage() + "\"\n}");
        }
    }

    @GetMapping("/detalle")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<Object> detalleUsuario(@RequestParam(value = "correo" , required = true) String correo) {
        try {
            UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioEmail(correo);
            return ResponseEntity.ok().body("{\n \"type\": \"success\" \n \"msg\": "+ usuarioEntity.toString() + "\n}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\n \"type\": \"error\" \n \"msg\": \""+ e.getMessage() + "\"\n}");
        }
    }

    @PutMapping("/cambiar")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<Object> cambiarContraseña(@Valid @RequestBody ContrasenaDTO contrasenaDTO) {
        try {
            String contrasenaEncriptada = passwordEncoder.encode(contrasenaDTO.getContrasena());
            usuarioService.cambiarContrasenia(contrasenaDTO.getId(), contrasenaEncriptada);
            return ResponseEntity.ok().body("{\n \"type\": \"success\" \n \"msg\": "+ "\"Contraseña cambiada exitosamente\"" + "\n}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\n \"type\": \"error\" \n \"msg\": \""+ e.getMessage() + "\"\n}");
        }
    }

}