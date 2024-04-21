package co.edu.comfanorte.splitter.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.comfanorte.splitter.model.entity.CursoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioCursoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioCursoKey;

@Repository
public interface UsuarioCursoRepository extends JpaRepository<UsuarioCursoEntity, UsuarioCursoKey> {
    
    List<UsuarioCursoEntity> findByUsuarioId(Integer usuarioId);
    List<UsuarioCursoEntity> findByCurso_Nombre(String nombreCurso);
}
