package BackendSiadseUfps.siadse.controller;


import BackendSiadseUfps.siadse.dto.ProjectDTO;
import BackendSiadseUfps.siadse.dto.SemilleroDTO;

import BackendSiadseUfps.siadse.service.interfaces.SemilleroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/semilleros")
public class SemilleroController {

    @Autowired
    private SemilleroService semilleroService;

    @PostMapping
    public ResponseEntity<SemilleroDTO> createSemillero(@RequestBody SemilleroDTO semilleroDTO) {
        SemilleroDTO createdSemillero = semilleroService.crearSemillero(semilleroDTO);
        return ResponseEntity.ok(createdSemillero);
    }
    @PostMapping("/{id}/asignarDirector/{directorId}")
    public ResponseEntity<SemilleroDTO> asignarDirector(@PathVariable Integer id, @PathVariable Integer directorId) {
        SemilleroDTO updatedSemillero = semilleroService.asignarDirector(id, directorId);
        return ResponseEntity.ok(updatedSemillero);
    }

    @PostMapping("/{semilleroId}/projects")
    public ResponseEntity<ProjectDTO> addProject(@PathVariable Integer semilleroId, @RequestBody ProjectDTO projectDTO) {
        // Lógica para obtener el semillero a partir de su ID
        SemilleroDTO semilleroDTO = semilleroService.getSemillero(semilleroId);
        if (semilleroDTO == null) {
            // Manejo de error si el semillero no se encuentra
            return ResponseEntity.notFound().build();
        }
        // Asignar el semillero al proyecto
        projectDTO.setSemillero(semilleroDTO);
        // Crear el proyecto en el semillero
        ProjectDTO createdProject = semilleroService.addProjectToSemillero(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @GetMapping
    public ResponseEntity<List<SemilleroDTO>> getAllSemilleros() {
        List<SemilleroDTO> semilleros = semilleroService.getAllSeedbeds();
        return ResponseEntity.ok(semilleros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemilleroDTO> getSemilleroById(@PathVariable Integer id) {
        SemilleroDTO semillero = semilleroService.getSeedbedId(id);
        if (semillero != null) {
            return ResponseEntity.ok(semillero);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SemilleroDTO> updateSemillero(@PathVariable Integer id, @RequestBody SemilleroDTO semilleroDTO) {
        SemilleroDTO updatedSemillero = semilleroService.updateSeedbed(id, semilleroDTO);
        if (updatedSemillero != null) {
            return ResponseEntity.ok(updatedSemillero);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemillero(@PathVariable Integer id) {
        try {
            semilleroService.deleteSeedbed(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
