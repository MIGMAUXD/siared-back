package BackendSiadseUfps.siadse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostShowDTO {

    String[] encabezados; 
    String[] contenidos;
    String imagenEncabezado;
    String tag;
    
}
