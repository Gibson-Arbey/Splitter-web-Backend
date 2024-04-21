package co.edu.comfanorte.splitter.service.interfaces;
import java.util.List;

import co.edu.comfanorte.splitter.model.entity.CursoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioCursoEntity;


public interface CursoInterface {
    List<UsuarioCursoEntity> getCursosByUsuarioId(Integer usuarioId);
    
}
