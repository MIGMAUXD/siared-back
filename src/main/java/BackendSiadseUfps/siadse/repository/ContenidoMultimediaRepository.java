package BackendSiadseUfps.siadse.repository;

import BackendSiadseUfps.siadse.entity.ContenidoMultimedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContenidoMultimediaRepository extends JpaRepository<ContenidoMultimedia, Integer> {
    List<ContenidoMultimedia> findByAlbumId(Integer albumId);


}
