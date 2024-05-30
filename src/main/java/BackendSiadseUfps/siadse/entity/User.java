
package BackendSiadseUfps.siadse.entity;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Size(min = 4, max =20 , message = "el Usuario debe tener menos y mas letras")
    @Column(nullable = false, unique = true) // Asegurarse de que el username es único y no nulo
    private String username; 
    
    @Size(max = 20, message = "el Usuario debe tener menos y mas letras")
    private String name;
    
    @Size(max = 20, message = "el Usuario debe tener menos y mas letras")
    @Column(nullable = false, unique = true) // Asegura que el email es único y no nulo
    private String email;
    
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    
    @Size(min = 8, message = "El codigo de la universidad debe tener al menos 7 caracteres")
    private String codigoUniversidad;
    
    @Max(value = 50, message = "El semestre actual debe ser al menos 50")
    private int semestreActual;
    
    @Min(value = 15, message = "La edad mínima es de 15 años")
    private int edad;
    
    @Size(max = 30, message = "La dirección de residencia debe tener como máximo 100 caracteres")
    private String direccionResidencia;
   
    @Pattern(regexp = "\\d{10}", message = "El número de celular debe tener 10 dígitos")
    private String celular;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
    
    @Column(name = "logged_in")
    private boolean loggedIn;
}
