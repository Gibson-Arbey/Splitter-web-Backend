package co.edu.comfanorte.splitter.service.interfaces;
import java.util.List;
import co.edu.comfanorte.splitter.model.entity.CursoEntity;


public interface CursoInterface {
   public List<CursoEntity> getCursosByUsuarioId(Integer usuarioId);
    
}
