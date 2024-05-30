package BackendSiadseUfps.siadse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100, message = "La descripción debe tener como máximo 300 caracteres")
    @NotNull(message = "titulo no puede ser  null")
    private String titulo;

    @Size(max = 300, message = "La descripción debe tener como máximo 300 caracteres")
    private String descripcion;

    private Date fechaCreacion;

    private Date fechaActualizacion;

    private String ubicacionAlbum;

    private String ruta;
}
