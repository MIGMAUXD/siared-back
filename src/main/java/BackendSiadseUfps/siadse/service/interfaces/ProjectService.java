package BackendSiadseUfps.siadse.service.interfaces;



import java.util.List;

import BackendSiadseUfps.siadse.dto.ProjectDTO;





public interface ProjectService {
	void  guardar(ProjectDTO projectDTO);
	ProjectDTO  buscarPorId(Long idSolicitud);

	
	ProjectDTO createProject(ProjectDTO projectDTO);
	ProjectDTO getProject(Long id);
	ProjectDTO updateProject(Long id, ProjectDTO projectDTO);
	void deleteProject(Long id);
	// public String saveFile(MultipartFile file);
	List<ProjectDTO> getAllProjects();
	
}
