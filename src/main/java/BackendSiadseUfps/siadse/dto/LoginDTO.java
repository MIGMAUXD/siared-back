package BackendSiadseUfps.siadse.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
    private String email;
    private String password;

    // Getters and Setters
}
