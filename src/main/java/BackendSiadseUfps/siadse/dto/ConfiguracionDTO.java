package BackendSiadseUfps.siadse.dto;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfiguracionDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @NotNull(message = "La sigla no puede ser nula")
    @NotBlank(message = "La sigla no puede estar en blanco")
    private String sigla;

    @NotNull(message = "La misión no puede ser nulo")
    @NotBlank(message = "La misión no puede estar en blanco")
    private String mision;

    @NotNull(message = "La visión no puede ser nulo")
    @NotBlank(message = "La visión no puede estar en blanco")
    private String vision;


    private String palabrasClave;

    @NotNull(message = "El director no puede ser nulo")
    @NotBlank(message = "El director no puede estar en blanco")
    private String director;

    private String whatsapp;

    private String instagram;

    private String facebook;

    private String correo;



}