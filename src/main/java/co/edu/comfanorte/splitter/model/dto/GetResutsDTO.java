package co.edu.comfanorte.splitter.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetResutsDTO {
    @NotNull(message = "Username no puede ir null")
    private String userName;
    @NotNull(message = "Tema no puede ir null")
    private String tema;
}
