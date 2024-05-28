package co.edu.comfanorte.splitter.service.interfaces;

import co.edu.comfanorte.splitter.model.dto.ResultadoDTO;
import co.edu.comfanorte.splitter.model.entity.ResultadoEntity;

import java.util.List;

public interface ResultadoInterface {

    void registerResult(ResultadoDTO resultado);

    List<ResultadoEntity> getResults(Integer nombreTema, Integer username);

    List<ResultadoEntity> getResultsByUser(Integer userId);
}
