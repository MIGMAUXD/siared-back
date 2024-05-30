package BackendSiadseUfps.siadse.service.implementations;

import BackendSiadseUfps.siadse.dto.EventoDTO;
import BackendSiadseUfps.siadse.entity.Evento;
import BackendSiadseUfps.siadse.entity.Semillero;
import BackendSiadseUfps.siadse.repository.EventoRepository;
import BackendSiadseUfps.siadse.repository.SemilleroRepository;
import BackendSiadseUfps.siadse.service.interfaces.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private SemilleroRepository semilleroRepository;

    @Override
    public EventoDTO crearEvento(EventoDTO eventoDTO) {
        Semillero semillero = semilleroRepository.findById(eventoDTO.getSemilleroId())
                .orElseThrow(() -> new RuntimeException("Semillero no encontrado"));

        Evento evento = new Evento();
        evento.setNombre(eventoDTO.getNombre());
        evento.setDescripcion(eventoDTO.getDescripcion());
        evento.setFechaInicio(eventoDTO.getFechaInicio());
        evento.setFechaFin(eventoDTO.getFechaFin());
        evento.setSemillero(semillero);

        evento = eventoRepository.save(evento);

        return convertirADTO(evento);
    }

    @Override
    public EventoDTO actualizarEvento(Integer id, EventoDTO eventoDTO) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        evento.setNombre(eventoDTO.getNombre());
        evento.setDescripcion(eventoDTO.getDescripcion());
        evento.setFechaInicio(eventoDTO.getFechaInicio());
        evento.setFechaFin(eventoDTO.getFechaFin());

        evento = eventoRepository.save(evento);

        return convertirADTO(evento);
    }

    @Override
    public List<EventoDTO> listarEventosPorSemillero(Integer semilleroId) {
        List<Evento> eventos = eventoRepository.findBySemilleroId(semilleroId);
        return eventos.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public EventoDTO buscarEventoPorId(Integer id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        return convertirADTO(evento);
    }

    @Override
    public void eliminarEvento(Integer id) {
        eventoRepository.deleteById(id);
    }

    private EventoDTO convertirADTO(Evento evento) {
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setId(evento.getId());
        eventoDTO.setNombre(evento.getNombre());
        eventoDTO.setDescripcion(evento.getDescripcion());
        eventoDTO.setFechaInicio(evento.getFechaInicio());
        eventoDTO.setFechaFin(evento.getFechaFin());
        eventoDTO.setSemilleroId(evento.getSemillero().getId());
        return eventoDTO;
    }
}
