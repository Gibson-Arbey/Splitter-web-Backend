package co.edu.comfanorte.splitter.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "historico_resultado")
public class HistoricoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tema_id")
    private TemaEntity tema;

    @Column(nullable = false)
    private float puntaje;

    @Column(nullable = false)
    private int anio;
}
