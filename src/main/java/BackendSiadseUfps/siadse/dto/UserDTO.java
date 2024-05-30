package BackendSiadseUfps.siadse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
	 private String username; 
    private String name;
    private String email;
    private String password;
    private String codigoUniversidad;
    private int semestreActual;
    private int edad;
    private String direccionResidencia;
    private String celular;
    private RoleDTO role;

    // Getters and Setters
}
