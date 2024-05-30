package BackendSiadseUfps.siadse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "comentario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "comment cannot be null")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "id_contenidos_multimedia", nullable = false)
    private ContenidoMultimedia contenidoMultimedia;

    private Date fechaCreacion;

    /*
     *  @ManyToOne
     *   @JoinColumn(name = "id_user", nullable = false)
     *   private Users user;
     */



}
