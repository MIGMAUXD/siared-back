package BackendSiadseUfps.siadse.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

}
