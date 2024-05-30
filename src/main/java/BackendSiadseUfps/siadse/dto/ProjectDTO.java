package BackendSiadseUfps.siadse.dto;



import BackendSiadseUfps.siadse.entity.Categoria;

import BackendSiadseUfps.siadse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
	
	private Long id;
	
	private String projectName;

	private String archivo;

	private String imagen = "no-image.png";

	private Categoria categoria; 
	
    private User projectLeader;  // Líder del proyecto, supongamos que es un User con roleId = 3
    
   
    private SemilleroDTO semillero;


	
}
