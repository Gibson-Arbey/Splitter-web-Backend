package co.edu.comfanorte.splitter.service.implementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.comfanorte.splitter.model.entity.UsuarioCursoEntity;
import co.edu.comfanorte.splitter.repository.UsuarioCursoRepository;
import co.edu.comfanorte.splitter.service.interfaces.CursoInterface;


@Service
public class CursoService implements CursoInterface{
    


    @Autowired
    private UsuarioCursoRepository usuarioCursoRepository;

    @Override
    public List<UsuarioCursoEntity> getCursosByUsuarioId(Integer usuarioId) {
        List<UsuarioCursoEntity> cursos = usuarioCursoRepository.findByUsuarioId(usuarioId);
        
        return cursos;
    }
    
}
