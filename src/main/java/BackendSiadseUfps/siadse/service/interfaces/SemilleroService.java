package BackendSiadseUfps.siadse.service.interfaces;

import java.util.List;

import BackendSiadseUfps.siadse.dto.SemilleroDTO;


public interface SemilleroService {
    public SemilleroDTO crearSemillero(SemilleroDTO semilleroDTO);
    SemilleroDTO asignarDirector(Integer semilleroId, Integer directorId);
    BackendSiadseUfps.siadse.dto.ProjectDTO addProjectToSemillero(BackendSiadseUfps.siadse.dto.ProjectDTO projectDTO);
    List<SemilleroDTO> getAllSeedbeds();
    SemilleroDTO getSeedbedId(Integer id);
    SemilleroDTO updateSeedbed(Integer id, SemilleroDTO seedbedDTO);

    public void deleteSeedbed(Integer id);
    public SemilleroDTO getSemillero(Integer semilleroId);


}