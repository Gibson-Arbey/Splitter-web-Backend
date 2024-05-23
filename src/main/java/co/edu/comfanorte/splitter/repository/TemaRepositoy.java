package co.edu.comfanorte.splitter.repository;


import co.edu.comfanorte.splitter.model.entity.TemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepositoy extends JpaRepository<TemaEntity, Integer> {
}
