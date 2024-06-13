package co.edu.comfanorte.splitter.service.interfaces;

import co.edu.comfanorte.splitter.model.entity.HistoricoEntity;

import java.util.List;

public interface HistoricoInterface {

    List<HistoricoEntity> generateHistorico();
    List<HistoricoEntity> getAll();

}
