package co.edu.comfanorte.splitter.model.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    private Integer idUsuario;

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

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = CursoEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "usuario_curso", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private Set<CursoEntity> cursos;

}
