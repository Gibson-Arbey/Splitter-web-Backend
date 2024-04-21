package co.edu.comfanorte.splitter.service.interfaces;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import co.edu.comfanorte.splitter.model.entity.UsuarioEntity;

public interface UsuarioInterface extends UserDetailsService{

    public void guardarUsuario(UsuarioEntity usuarioEntity, String curso);

    public void cambiarContrasenia(Integer id, String contrasena);

    public UsuarioEntity buscarUsuarioEmail(String correo);

    public List<UsuarioEntity> listarEstudiantes(String curso);
}
