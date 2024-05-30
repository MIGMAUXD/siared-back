package BackendSiadseUfps.siadse.service.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import BackendSiadseUfps.siadse.dto.ProjectDTO;
import BackendSiadseUfps.siadse.entity.Project;

import BackendSiadseUfps.siadse.repository.ProjectRepository;

import BackendSiadseUfps.siadse.service.interfaces.ProjectService;


@Service
public class ProjectServiceImpl implements ProjectService {
	private final ModelMapper modelMapper;
	@Autowired
	private ProjectRepository projectRepository;

	 public ProjectServiceImpl(ModelMapper modelMapper,  ProjectRepository projectRepository ) {
	        this.modelMapper = modelMapper;
	        this.projectRepository=projectRepository;
	    }
	@Override
	public ProjectDTO createProject(ProjectDTO projectDTO) {
		Project project = new ModelMapper().map(projectDTO, Project.class);
		project = projectRepository.save(project);
		return new ModelMapper().map(project, ProjectDTO.class);
	}

	@Override
	public ProjectDTO getProject(Long id) {
		Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
		return new ModelMapper().map(project, ProjectDTO.class);
	}

	@Override
	public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
		Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
		new ModelMapper().map(projectDTO, project);
		project = projectRepository.save(project);
		return new ModelMapper().map(project, ProjectDTO.class);
	}

	@Override
	public void deleteProject(Long id) {
		projectRepository.deleteById(id);
	}
	/*
	 * @Override public String saveFile(MultipartFile file) { // Logic to save file
	 * goes here return "Path_to_file"; // Return the path where the file is saved }
	 */

	@Override
	public void guardar(ProjectDTO projectDTO) {
		Project project = new ModelMapper().map(projectDTO, Project.class);
		projectRepository.save(project);
	}

	@Override
	public ProjectDTO buscarPorId(Long idSolicitud) {
		Optional<Project> optional = projectRepository.findById(idSolicitud);
		if (optional.isPresent()) {
			return new ModelMapper().map(optional.get(), ProjectDTO.class);
		}
		return null;
	}

	@Override
	public List<ProjectDTO> getAllProjects() {
		List<Project> projects = projectRepository.findAll();
		// Convertir la lista de proyectos a una lista de ProjectDTO
		List<ProjectDTO> projectDTOs = projects.stream().map(project -> modelMapper.map(project, ProjectDTO.class))
				.collect(Collectors.toList());
		return projectDTOs;
	}

}
