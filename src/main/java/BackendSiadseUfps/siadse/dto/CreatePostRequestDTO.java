package BackendSiadseUfps.siadse.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePostRequestDTO {

    String titulo;
    String imagenEncabezado;
    String encabezados;
    String contenido;
    String tag;

}
