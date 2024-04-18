package co.edu.comfanorte.splitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.comfanorte.splitter.model.entity.RolEntity;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {

}
