package BackendSiadseUfps.siadse.service.interfaces;

import BackendSiadseUfps.siadse.dto.AsistenciaDTO;

import java.util.List;

public interface AsistenciaService {
    AsistenciaDTO registrarAsistencia(AsistenciaDTO asistenciaDTO);
    AsistenciaDTO actualizarAsistencia(Integer id, AsistenciaDTO asistenciaDTO);
    List<AsistenciaDTO> listarAsistencias();
    AsistenciaDTO buscarAsistenciaPorId(Integer id);
    void eliminarAsistencia(Integer id);
}
