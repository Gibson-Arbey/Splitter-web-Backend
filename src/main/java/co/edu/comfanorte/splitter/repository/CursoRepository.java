package co.edu.comfanorte.splitter.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.comfanorte.splitter.model.entity.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Integer>{
    Optional<CursoEntity> findByNombre(String nombre);
    
    
}
