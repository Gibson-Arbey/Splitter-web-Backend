package co.edu.comfanorte.splitter.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResultadosDTO {

    private float puntaje;
    private String username;
    private String tema;
}
