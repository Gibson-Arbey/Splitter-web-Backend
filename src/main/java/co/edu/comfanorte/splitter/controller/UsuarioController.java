package co.edu.comfanorte.splitter.controller;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.comfanorte.splitter.model.dto.ContrasenaDTO;
import co.edu.comfanorte.splitter.model.dto.EstudiantesDTO;
import co.edu.comfanorte.splitter.model.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> guardarEstudiante(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            BeanUtils.copyProperties(usuarioDTO, usuarioEntity);
            usuarioEntity.setContrasena(passwordEncoder.encode(usuarioDTO.getContrasenaDesencriptada()));
            usuarioService.guardarUsuario(usuarioEntity, usuarioDTO.getCurso());

            return ResponseEntity.ok().body(new ResponseDTO("success", "Estudiante registrado con exito."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO("error", e.getMessage()));
        }
    }

    @GetMapping("/detalle")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<ResponseDTO> detalleUsuario(@RequestParam(value = "correo" , required = true) String correo) {
        try {
            UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioEmail(correo);
            return ResponseEntity.ok().body(new ResponseDTO("success", usuarioEntity.toString()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO("error", e.getMessage()));
        }
    }

    @PutMapping("/cambiar")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<ResponseDTO> cambiarContraseña(@Valid @RequestBody ContrasenaDTO contrasenaDTO) {
        try {
            String contrasenaEncriptada = passwordEncoder.encode(contrasenaDTO.getContrasena());
            usuarioService.cambiarContrasenia(contrasenaDTO.getId(), contrasenaEncriptada);
            return ResponseEntity.ok().body(new ResponseDTO("success", "Contraseña cambiada exitosamente."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO("error", e.getMessage()));
        }
    }

    @GetMapping("/{curso}")
    @PreAuthorize("hasAuthority('ROL_PROFESOR')")
    public ResponseEntity<ResponseDTO> listarEstudiantes(@PathVariable(value = "curso" , required = true) String curso) {
        try {
            List<EstudiantesDTO> estudiantes = usuarioService.listarEstudiantes(curso);
            return ResponseEntity.ok().body(new ResponseDTO("success", estudiantes));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO("error", e.getMessage()));
        }
    }

}