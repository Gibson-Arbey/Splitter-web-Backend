package co.edu.comfanorte.splitter.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetResutsDTO {
    @NotNull(message = "id del usuario no puede ir null")
    private Integer idUser;
    @NotNull(message = "id del Tema no puede ir null")
    private Integer idTema;
}
