package co.edu.comfanorte.splitter.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    
    @NotBlank( message = "El type no puede estar vacio")
    private String type;

    @NotNull(message = "El mensaje no puede ser nulo")
    private Object msg;
}
