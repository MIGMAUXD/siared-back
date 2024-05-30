package BackendSiadseUfps.siadse.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO {

    private Integer id;

    private String titulo;

    private String descripcion;

    private Date fechaCreacion;

    private Date fechaActualizacion;

    private String ubicacionAlbum;

    private String ruta;
}
