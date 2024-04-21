package co.edu.comfanorte.splitter.service.interfaces;
import java.util.List;

import co.edu.comfanorte.splitter.model.dto.CursoDTO;
import co.edu.comfanorte.splitter.model.entity.CursoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioCursoEntity;


public interface CursoInterface {
    List<CursoEntity> getCursosByUsuarioId(Integer usuarioId);
    
}
