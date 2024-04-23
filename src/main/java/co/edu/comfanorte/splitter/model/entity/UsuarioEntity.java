package co.edu.comfanorte.splitter.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase de entidad que representa la tabla "usuario" en la base de datos.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario",  indexes = {@Index(columnList = "correo", name = "index_correo", unique = true) })
public class UsuarioEntity {
    
    /* *
     * Id del usuario
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    /* *
     * Nombres del usuario
     */
    @Column(nullable = false, length = 25)
    @NotEmpty
    private String nombre;

    /* *
     * Apellidos del usuario
     */
    @Column(nullable = false, length = 25)
    @NotEmpty
    private String apellido;

    /* *
     * Correo del usuario
     */
    @Column(nullable = false, length = 255)
    @NotEmpty
    private String correo;

    /* *
     * contraseña encriptada del usuario
     */
    @Column(nullable = false, length = 255)
    @NotEmpty
    private String contrasena;

    /* *
     * Rol del usuario
     */
    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private RolEntity rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UsuarioCursoEntity> usuarioCursos;

    @Override
    public String toString() {
        return "{ id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + " }";
    }

    
}
