package co.edu.comfanorte.splitter.util;

import co.edu.comfanorte.splitter.model.dto.HistoricoDTO;
import co.edu.comfanorte.splitter.model.dto.ResultadoDTO;
import co.edu.comfanorte.splitter.model.dto.ResultadosDTO;
import co.edu.comfanorte.splitter.model.dto.TemaDTO;
import co.edu.comfanorte.splitter.model.entity.HistoricoEntity;
import co.edu.comfanorte.splitter.model.entity.ResultadoEntity;
import co.edu.comfanorte.splitter.model.entity.TemaEntity;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper {

    public static ResultadosDTO fromEntityToDto(ResultadoEntity resultadoEntity){
        return ResultadosDTO.builder().puntaje(resultadoEntity.getPuntaje())
                .username(resultadoEntity.getUsuario().getNombre())
                .tema(resultadoEntity.getTema().getNombre()).build();
    }

    public static TemaDTO fromEntityToDto(TemaEntity temaEntity){
        return TemaDTO.builder().id(temaEntity.getId())
                .nombre(temaEntity.getNombre()).build();
    }

    public static HistoricoDTO fromEntityToDto(HistoricoEntity historicoEntity){
        return HistoricoDTO.builder()
                .anio(historicoEntity.getAnio())
                .puntaje(historicoEntity.getPuntaje())
                .tema(historicoEntity.getTema()
                        .getId()).build();
    }


}
