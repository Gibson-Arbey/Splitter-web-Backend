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
import org.springframework.transaction.annotation.Transactional;

import co.edu.comfanorte.splitter.exception.UsuarioException;
import co.edu.comfanorte.splitter.model.entity.CursoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioCursoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioCursoKey;
import co.edu.comfanorte.splitter.model.entity.UsuarioEntity;
import co.edu.comfanorte.splitter.repository.CursoRepository;
import co.edu.comfanorte.splitter.repository.RolRepository;
import co.edu.comfanorte.splitter.repository.UsuarioCursoRepository;
import co.edu.comfanorte.splitter.repository.UsuarioRepository;
import co.edu.comfanorte.splitter.service.interfaces.UsuarioInterface;

@Service
public class UsuarioService implements UsuarioInterface{

    @Autowired
    private  UsuarioRepository usuarioRepository;
    
    @Autowired
    private  RolRepository rolRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioCursoRepository usuarioCursoRepository;
    
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
    @Transactional(rollbackFor = Exception.class)
	public void guardarUsuario(UsuarioEntity usuarioEntity, String curso) {  
        try {

            if (usuarioRepository.findByCorreo(usuarioEntity.getCorreo()).isPresent()) {
                throw new UsuarioException("El correo electronico ya esta registrado.");
            }

            //Guardar estudiante
            usuarioEntity.setRol(rolRepository.findByNombre("ROL_ESTUDIANTE"));
            UsuarioEntity usuarioDB = usuarioRepository.save(usuarioEntity);

            //Buscar curso
            Optional<CursoEntity> cursoOptional = cursoRepository.findByNombre(curso);
            if (!cursoOptional.isPresent()) {
                throw new UsuarioException("El curso no fue encontrado.");
            }

            //Crear llave primaria UsuarioCurso
            UsuarioCursoKey usuarioCursoKey = new UsuarioCursoKey();
            usuarioCursoKey.setUsuarioId(usuarioDB.getId());
            usuarioCursoKey.setCursoId(cursoOptional.get().getId());

            //Guardar relacion UsuarioCurso
            UsuarioCursoEntity usuarioCurso = new UsuarioCursoEntity();
            usuarioCurso.setId(usuarioCursoKey);
            usuarioCurso.setUsuario(usuarioEntity);
            usuarioCurso.setCurso(cursoOptional.get());
            usuarioCursoRepository.save(usuarioCurso);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
	}

	@Override
	public void cambiarContrasenia(Integer id, String contrasena) {
		try {
            Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);

            if (!usuarioOptional.isPresent()) {
            throw new UsuarioException("El usuario no fue encontrado.");
            }
            UsuarioEntity usuarioEntity = usuarioOptional.get();
            usuarioEntity.setContrasena(contrasena);
            usuarioRepository.save(usuarioEntity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
	}

	@Override
	public UsuarioEntity buscarUsuarioEmail(String correo) {
		try {
            Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findByCorreo(correo);

            if (!usuarioOptional.isPresent()) {
                throw new UsuarioException("El usuario no fue encontrado.");
            }

            return usuarioOptional.get();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
	}

	@Override
	public List<UsuarioEntity> listarEstudiantes() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'listarEstudiantes'");
	}
    
}
