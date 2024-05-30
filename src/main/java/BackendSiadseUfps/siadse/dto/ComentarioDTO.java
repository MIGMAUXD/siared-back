package BackendSiadseUfps.siadse.dto;

import BackendSiadseUfps.siadse.entity.ContenidoMultimedia;

import lombok.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {
    private Integer id;

    private String comentario;

    private Integer contenidoMultimediaId;

    private Date fechaCreacion;

    private Integer userId;

}
