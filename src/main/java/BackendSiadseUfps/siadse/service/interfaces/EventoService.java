package BackendSiadseUfps.siadse.service.interfaces;

import BackendSiadseUfps.siadse.dto.EventoDTO;

import java.util.List;

public interface EventoService {
    EventoDTO crearEvento(EventoDTO eventoDTO);
    EventoDTO actualizarEvento(Integer id, EventoDTO eventoDTO);
    List<EventoDTO> listarEventosPorSemillero(Integer semilleroId);
    EventoDTO buscarEventoPorId(Integer id);
    void eliminarEvento(Integer id);
}
