package BackendSiadseUfps.siadse.repository;

import BackendSiadseUfps.siadse.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findBySemilleroId(Integer semilleroId);
}
