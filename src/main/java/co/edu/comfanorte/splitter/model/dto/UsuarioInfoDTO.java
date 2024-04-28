package co.edu.comfanorte.splitter.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioInfoDTO {
    
    /**
     * El ID del usuario.
     */
    private Integer id;

    /**
     * El nombre del usuario.
     */
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 25, message = "El nombre debe tener maximo 25 caracteres.")
    private String nombre;

    /**
     * El apellido del usuario.
     */
    @NotBlank(message = "El apellido no puede estar vacío.")
    @Size(max = 25, message = "El apellido debe tener maximo 25 caracteres.")
    private String apellido;
    /**
     * El correo electrónico del usuario.
     */
    @NotBlank(message = "El correo no puede estar vacío.")
    @Size(max = 255, message = "El correo debe tener maximo 255 caracteres.")
    private String correo;
}
