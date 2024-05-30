package BackendSiadseUfps.siadse.dto;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SemilleroDTO {

    private Integer id;

    private String nombre;

    private String descripcion;

    private Date fechaCreacion;

    private Date fechaActualizacion;

    private DirectorDTO director;


}