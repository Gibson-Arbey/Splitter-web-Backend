package co.edu.comfanorte.splitter.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import co.edu.comfanorte.splitter.model.entity.UsuarioEntity;

public interface UsuarioInterface extends UserDetailsService{

    public void guardarUsuario(UsuarioEntity usuarioEntity);

    public void cambiarContrasenia(String email, String contrasenia, String contraseniaEncriptada);

    public UsuarioEntity buscarUsuarioEmail(String email);

    public Optional<List<UsuarioEntity>>  listarEstudiantes();
}
