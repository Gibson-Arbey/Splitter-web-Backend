package co.edu.comfanorte.splitter.repository;

import co.edu.comfanorte.splitter.model.entity.CursoEntity;
import co.edu.comfanorte.splitter.model.entity.HistoricoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<HistoricoEntity, Integer> {

    @Query("SELECT a FROM HistoricoEntity a ORDER BY a.tema")
    List<HistoricoEntity> finAllOrderByTema();

    List<HistoricoEntity> findAllByAnio(int year);
}
