package BackendSiadseUfps.siadse.service.implementations;

import BackendSiadseUfps.siadse.dto.AsistenciaDTO;
import BackendSiadseUfps.siadse.entity.Asistencia;
import BackendSiadseUfps.siadse.entity.Evento;
import BackendSiadseUfps.siadse.entity.User;
import BackendSiadseUfps.siadse.repository.AsistenciaRepository;
import BackendSiadseUfps.siadse.repository.EventoRepository;
import BackendSiadseUfps.siadse.repository.UserRepository;
import BackendSiadseUfps.siadse.service.interfaces.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public AsistenciaDTO registrarAsistencia(AsistenciaDTO asistenciaDTO) {
        User user = userRepository.findById(asistenciaDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Evento evento = eventoRepository.findById(asistenciaDTO.getEventoId())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        Asistencia asistencia = new Asistencia();
        asistencia.setUser(user);
        asistencia.setEvento(evento);

        asistencia = asistenciaRepository.save(asistencia);

        return convertirADTO(asistencia);
    }

    @Override
    public AsistenciaDTO actualizarAsistencia(Integer id, AsistenciaDTO asistenciaDTO) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));

        User user = userRepository.findById(asistenciaDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Evento evento = eventoRepository.findById(asistenciaDTO.getEventoId())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        asistencia.setUser(user);
        asistencia.setEvento(evento);

        asistencia = asistenciaRepository.save(asistencia);

        return convertirADTO(asistencia);
    }

    @Override
    public List<AsistenciaDTO> listarAsistencias() {
        List<Asistencia> asistencias = asistenciaRepository.findAll();
        return asistencias.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public AsistenciaDTO buscarAsistenciaPorId(Integer id) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));
        return convertirADTO(asistencia);
    }

    @Override
    public void eliminarAsistencia(Integer id) {
        asistenciaRepository.deleteById(id);
    }

    private AsistenciaDTO convertirADTO(Asistencia asistencia) {
        AsistenciaDTO asistenciaDTO = new AsistenciaDTO();
        asistenciaDTO.setId(asistencia.getId());
        asistenciaDTO.setUserId(asistencia.getUser().getId());
        asistenciaDTO.setEventoId(asistencia.getEvento().getId());
        return asistenciaDTO;
    }
}
