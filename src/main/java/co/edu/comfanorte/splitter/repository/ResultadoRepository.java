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

    @Query("SELECT r from ResultadoEntity r WHERE r.tema.id=:idTema and r.usuario.id=:idUser")
    List<ResultadoEntity> findByUserAndTema(@Param("idTema") Integer idTema, @Param("idUser") Integer idUser);

    @Query("SELECT r from ResultadoEntity  r WHERE r.usuario.id=:userId")
    List<ResultadoEntity> findAllByUser(@Param("userId") Integer userId);

    @Query("SELECT r FROM ResultadoEntity r WHERE YEAR(r.createdDate) = :year ORDER BY r.tema")
    List<ResultadoEntity> findAllOrderBy(@Param("year") int year);
}
