package BackendSiadseUfps.siadse.controller;

import BackendSiadseUfps.siadse.dto.EventoDTO;
import BackendSiadseUfps.siadse.service.interfaces.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoDTO> crearEvento(@RequestBody EventoDTO eventoDTO) {
        EventoDTO eventoCreado = eventoService.crearEvento(eventoDTO);
        return new ResponseEntity<>(eventoCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> actualizarEvento(@PathVariable Integer id, @RequestBody EventoDTO eventoDTO) {
        EventoDTO eventoActualizado = eventoService.actualizarEvento(id, eventoDTO);
        return new ResponseEntity<>(eventoActualizado, HttpStatus.OK);
    }

    @GetMapping("/semillero/{semilleroId}")
    public ResponseEntity<List<EventoDTO>> listarEventosPorSemillero(@PathVariable Integer semilleroId) {
        List<EventoDTO> eventos = eventoService.listarEventosPorSemillero(semilleroId);
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> buscarEventoPorId(@PathVariable Integer id) {
        EventoDTO evento = eventoService.buscarEventoPorId(id);
        return new ResponseEntity<>(evento, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Integer id) {
        eventoService.eliminarEvento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
