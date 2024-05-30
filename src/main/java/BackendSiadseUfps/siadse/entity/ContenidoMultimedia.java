package BackendSiadseUfps.siadse.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "contenido_multimedia")
public class ContenidoMultimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    private Date fechaSubida;


    @Column(nullable = true)
    private String url;

    private String ruta;

    private String formato;

    @ManyToOne
    @JoinColumn(name = "id_album", nullable = false)
    private Album album;
}
