package co.edu.comfanorte.splitter.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.comfanorte.splitter.model.entity.CursoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioCursoEntity;
import co.edu.comfanorte.splitter.model.entity.UsuarioCursoKey;

@Repository
public interface UsuarioCursoRepository extends JpaRepository<UsuarioCursoEntity, UsuarioCursoKey> {
    
    // @Query("SELECT c FROM curso c JOIN c.usuario_curso uc WHERE uc.usuario_id = :usuarioId")
    List<UsuarioCursoEntity> findByUsuarioId(Integer usuarioId);
}
