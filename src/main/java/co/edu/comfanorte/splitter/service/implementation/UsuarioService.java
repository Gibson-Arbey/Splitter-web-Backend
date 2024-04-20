package co.edu.comfanorte.splitter.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.comfanorte.splitter.exception.UsuarioException;
import co.edu.comfanorte.splitter.model.entity.UsuarioEntity;
import co.edu.comfanorte.splitter.repository.RolRepository;
import co.edu.comfanorte.splitter.repository.UsuarioRepository;
import co.edu.comfanorte.splitter.service.interfaces.UsuarioInterface;

@Service
public class UsuarioService implements UsuarioInterface{

    @Autowired
    private  UsuarioRepository usuarioRepository;
    
    @Autowired
    private  RolRepository rolRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity userEntity = usuarioRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (userEntity.getRol() != null) {
            authorities.add(new SimpleGrantedAuthority(userEntity.getRol().getNombre()));
        } else {
            throw new UsernameNotFoundException(
                    "Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }
        return new User(userEntity.getCorreo(), userEntity.getContrasena(), authorities);
    }

	@Override
	public void guardarUsuario(UsuarioEntity usuarioEntity) {
		if (usuarioRepository.findByCorreo(usuarioEntity.getCorreo()).isPresent()) {
            throw new UsuarioException("El correo electronico ya existe");
        }

        if(usuarioEntity.getContrasena().length() < 8){
            throw new UsuarioException("La contraseña debe tener minimo 8 caracteres");
        }
        
        try {

            usuarioEntity.setRol(rolRepository.findByNombre("ROL_ESTUDIANTE"));
            usuarioRepository.save(usuarioEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar al usuario:" + e.getMessage());
        }
	}

	@Override
	public void cambiarContrasenia(String email, String contrasenia, String contraseniaEncriptada) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cambiarContrasenia'");
	}

	@Override
	public UsuarioEntity buscarUsuarioEmail(String email) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'buscarUsuarioEmail'");
	}

	@Override
	public Optional<List<UsuarioEntity>> listarEstudiantes() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'listarEstudiantes'");
	}
    
}
