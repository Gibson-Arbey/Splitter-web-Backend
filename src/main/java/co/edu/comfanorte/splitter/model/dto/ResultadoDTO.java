package co.edu.comfanorte.splitter.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultadoDTO {

    @NotNull(message = "puntaje no debe ser null")
    private float puntaje;

    @NotNull(message = "tema no debe ser null")
    private Integer idTema;

    @NotNull(message = "user no debe ser null")
    private Integer idUser;
}
