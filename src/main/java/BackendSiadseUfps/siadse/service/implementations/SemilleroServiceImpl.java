package BackendSiadseUfps.siadse.service.implementations;


import BackendSiadseUfps.siadse.dto.ProjectDTO;
import BackendSiadseUfps.siadse.dto.SemilleroDTO;
import BackendSiadseUfps.siadse.entity.Project;
import BackendSiadseUfps.siadse.entity.Semillero;
import BackendSiadseUfps.siadse.entity.User;
import BackendSiadseUfps.siadse.repository.ProjectRepository;
import BackendSiadseUfps.siadse.repository.SemilleroRepository;
import BackendSiadseUfps.siadse.repository.UserRepository;
import BackendSiadseUfps.siadse.service.interfaces.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SemilleroServiceImpl implements SemilleroService {

    private final ModelMapper modelMapper;
    @Autowired
    private SemilleroRepository semilleroRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;


    public SemilleroServiceImpl(ModelMapper modelMapper, SemilleroRepository semilleroRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.semilleroRepository = semilleroRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SemilleroDTO crearSemillero(SemilleroDTO semilleroDTO) {
        Semillero semillero = modelMapper.map(semilleroDTO, Semillero.class);
        semillero = semilleroRepository.save(semillero);
        return modelMapper.map(semillero, SemilleroDTO.class);
    }
    @Override
    public SemilleroDTO asignarDirector(Integer semilleroId, Integer directorId) {
        Semillero semillero = semilleroRepository.findById(semilleroId)
                .orElseThrow(() -> new RuntimeException("Semillero no encontrado"));
        User director = userRepository.findById(directorId)
                .orElseThrow(() -> new RuntimeException("Director no encontrado"));
        semillero.setDirector(director);
        semillero = semilleroRepository.save(semillero);
        return modelMapper.map(semillero, SemilleroDTO.class);
    }
    @Override
    public ProjectDTO addProjectToSemillero(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectLeader(userRepository.findById(projectDTO.getProjectLeader()).orElseThrow());
        project.setSemillero(semilleroRepository.findById(projectDTO.getSemillero()).orElseThrow());
        projectRepository.save(project);
        return modelMapper.map(project, ProjectDTO.class);
    }

    @Override
    public List<SemilleroDTO> getAllSeedbeds() {
        List<Semillero> semilleros = semilleroRepository.findAll();
        return semilleros.stream()
                .map(semillero -> modelMapper.map(semillero, SemilleroDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SemilleroDTO getSeedbedId(Integer id) {
        Optional<Semillero> optionalSemillero = semilleroRepository.findById(id);
        return optionalSemillero.map(semillero -> modelMapper.map(semillero, SemilleroDTO.class))
                .orElse(null);
    }

    @Override
    public SemilleroDTO updateSeedbed(Integer id, SemilleroDTO seedbedDTO) {
        Optional<Semillero> optionalSemillero = semilleroRepository.findById(id);
        if (optionalSemillero.isPresent()) {
            Semillero semillero = optionalSemillero.get();
            semillero.setNombre(seedbedDTO.getNombre());
            semillero.setDescripcion(seedbedDTO.getDescripcion());
            semillero.setFechaActualizacion(new Date());
            semilleroRepository.save(semillero);
            return modelMapper.map(semillero, SemilleroDTO.class);
        } else {
            return null;
        }
    }


    @Override
    public void deleteSeedbed(Integer id) {
        if (semilleroRepository.existsById(id)) {
            semilleroRepository.deleteById(id);
        } else {
            throw new RuntimeException("Semillero not found");
        }
    }
    @Override
    public SemilleroDTO getSemillero(Integer semilleroId) {
        Semillero semillero = semilleroRepository.findById(semilleroId)
                .orElseThrow(() -> new RuntimeException("Semillero not found"));
        return new ModelMapper().map(semillero, SemilleroDTO.class);
    }


}