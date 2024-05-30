package BackendSiadseUfps.siadse.dto;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContenidoMutimediaDTO {
    private Integer id;


    private String titulo;

    private Date fechaSubida;

    private String url;

    private String formato;

    private String ruta;

    private Integer albumId;
}
