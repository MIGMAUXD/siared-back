package BackendSiadseUfps.siadse.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecentPostDTO {
    
    private String titulo;
    private Date fechaCreacion;
    private String tag;
    private String imagenEncabezado; // La imagen codificada en base64
    
    private String uniqueTitleId; //ejemplo: Titulo: Hola como estas -> hola-como-estas

}

