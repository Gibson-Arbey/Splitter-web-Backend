package co.edu.comfanorte.splitter.model.dto;

import co.edu.comfanorte.splitter.model.entity.TemaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HistoricoDTO {


    private int tema;


    private float puntaje;


    private int anio;
}
