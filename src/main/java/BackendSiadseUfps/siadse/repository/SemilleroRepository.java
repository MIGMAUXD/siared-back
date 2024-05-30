package BackendSiadseUfps.siadse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import BackendSiadseUfps.siadse.dto.SemilleroDTO;
import BackendSiadseUfps.siadse.entity.Semillero;


public interface SemilleroRepository extends JpaRepository<Semillero,Integer> {
	Optional<Semillero> findById(SemilleroDTO semilleroDTO);

	Semillero getReferenceById(Integer semilleroId);

}