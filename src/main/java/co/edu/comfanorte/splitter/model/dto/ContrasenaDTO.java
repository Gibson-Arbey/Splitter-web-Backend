package co.edu.comfanorte.splitter.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase para cambiar contrasena
 */
@Getter
@Setter
public class ContrasenaDTO {
    
    /**
     * El ID del usuario.
     */
    @NotNull(message = "El id no puede estar vacío.")
    private Integer id;

    /**
     * La contraseña del usuario (sin encriptar).
     */
    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 8, message = "La contraseña debe tener minimo 8 caracteres.")
    private String contrasena;
}
