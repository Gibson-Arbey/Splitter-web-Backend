package co.edu.comfanorte.splitter.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class UsuarioCursoKey implements Serializable {

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "curso_id")
    private Integer cursoId;

}
