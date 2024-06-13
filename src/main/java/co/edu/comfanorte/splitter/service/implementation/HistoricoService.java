package co.edu.comfanorte.splitter.service.implementation;

import co.edu.comfanorte.splitter.exception.ReportAlreadyGeneratedException;
import co.edu.comfanorte.splitter.model.entity.HistoricoEntity;
import co.edu.comfanorte.splitter.model.entity.ResultadoEntity;
import co.edu.comfanorte.splitter.model.entity.TemaEntity;
import co.edu.comfanorte.splitter.repository.HistoricoRepository;
import co.edu.comfanorte.splitter.repository.ResultadoRepository;
import co.edu.comfanorte.splitter.service.interfaces.HistoricoInterface;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Data
public class HistoricoService implements HistoricoInterface {

    private final HistoricoRepository repository;
    private final ResultadoRepository resultadoRepository;
    @Override
    public List<HistoricoEntity> generateHistorico() {
        int year = LocalDate.now().getYear();
        List<HistoricoEntity> historicoEntities= repository.findAllByAnio(year);
        if(!historicoEntities.isEmpty()){
            throw new ReportAlreadyGeneratedException("Reporte de el año " +year+" ya fue generado");
        }
        List<ResultadoEntity> results=resultadoRepository.findAllOrderBy(year);
        Map<TemaEntity, List<ResultadoEntity>> resultsByTema= results.stream().collect(Collectors.groupingBy(ResultadoEntity::getTema));
        List<HistoricoEntity> averages=new ArrayList<>();
        resultsByTema.forEach((tema, resultsBy) -> {
            averages.add(getAverage(resultsBy, year));
        });
        return repository.saveAll(averages);
    }

    @Override
    public List<HistoricoEntity> getAll() {
        return repository.findAll();
    }

    private HistoricoEntity getAverage(List<ResultadoEntity> results, int year){
        double average=results.stream().mapToDouble(ResultadoEntity::getPuntaje).sum()/results.size();
        return HistoricoEntity.builder().anio(year).tema(results.get(0).getTema()).puntaje((float) average).build();
    }
}
