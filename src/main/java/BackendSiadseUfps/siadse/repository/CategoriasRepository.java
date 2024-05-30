package BackendSiadseUfps.siadse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BackendSiadseUfps.siadse.entity.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
}