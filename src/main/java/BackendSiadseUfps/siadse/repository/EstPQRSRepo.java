package BackendSiadseUfps.siadse.repository;

import BackendSiadseUfps.siadse.entity.EstadosPQRS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstPQRSRepo extends JpaRepository<EstadosPQRS, Integer> {
    EstadosPQRS findByEstado (String estado);
}