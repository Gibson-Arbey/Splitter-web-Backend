package co.edu.comfanorte.splitter.repository;

import co.edu.comfanorte.splitter.model.entity.ResultadoEntity;
import co.edu.comfanorte.splitter.model.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoEntity, Integer> {

    @Query("SELECT r from ResultadoEntity r WHERE r.tema.nombre=:nombreTema and r.usuario.nombre=:usename")
    List<ResultadoEntity> findByUserAndTema(@Param("nombreTema") String nombreTema, @Param("usename") String username);
}
