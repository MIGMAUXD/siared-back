package BackendSiadseUfps.siadse.controller;

import BackendSiadseUfps.siadse.dto.AsistenciaDTO;
import BackendSiadseUfps.siadse.service.interfaces.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @PostMapping
    public ResponseEntity<AsistenciaDTO> registrarAsistencia(@RequestBody AsistenciaDTO asistenciaDTO) {
        AsistenciaDTO asistenciaRegistrada = asistenciaService.registrarAsistencia(asistenciaDTO);
        return new ResponseEntity<>(asistenciaRegistrada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> actualizarAsistencia(@PathVariable Integer id, @RequestBody AsistenciaDTO asistenciaDTO) {
        AsistenciaDTO asistenciaActualizada = asistenciaService.actualizarAsistencia(id, asistenciaDTO);
        return new ResponseEntity<>(asistenciaActualizada, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AsistenciaDTO>> listarAsistencias() {
        List<AsistenciaDTO> asistencias = asistenciaService.listarAsistencias();
        return new ResponseEntity<>(asistencias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> buscarAsistenciaPorId(@PathVariable Integer id) {
        AsistenciaDTO asistencia = asistenciaService.buscarAsistenciaPorId(id);
        return new ResponseEntity<>(asistencia, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsistencia(@PathVariable Integer id) {
        asistenciaService.eliminarAsistencia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
