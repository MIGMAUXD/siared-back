package BackendSiadseUfps.siadse.repository;

import BackendSiadseUfps.siadse.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
    List<Asistencia> findByUserId(Integer userId);
    List<Asistencia> findByEventoId(Integer eventoId);
}
