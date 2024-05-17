package co.edu.comfanorte.splitter.controller;

import co.edu.comfanorte.splitter.model.dto.ResponseDTO;
import co.edu.comfanorte.splitter.model.entity.UsuarioEntity;
import co.edu.comfanorte.splitter.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UsuarioEntity loginRequest) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getCorreo(), loginRequest.getContrasena());

            Authentication authentication = authenticationManager.authenticate(authToken);


            User user = (User) authentication.getPrincipal();
            String rol = user.getAuthorities().iterator().next().getAuthority();
            String token=jwtUtil.generateAccesToken(user.getUsername(), rol);
            return ResponseEntity.ok(ResponseDTO.builder().msg(token).type("success").build());
        } catch (AuthenticationException e) {
            return ResponseEntity.ok( ResponseDTO.builder().type("error").msg("Correo o contraseña invalidos.").build());
        }
    }

}
