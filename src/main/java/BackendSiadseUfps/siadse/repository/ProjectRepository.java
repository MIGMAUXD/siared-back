package BackendSiadseUfps.siadse.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import BackendSiadseUfps.siadse.dto.ProjectDTO;

import BackendSiadseUfps.siadse.entity.Project;


import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	Optional<Project> findById(ProjectDTO projectDTO);
   
}
