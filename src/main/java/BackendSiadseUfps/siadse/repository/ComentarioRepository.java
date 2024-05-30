package BackendSiadseUfps.siadse.repository;

import BackendSiadseUfps.siadse.entity.Comentario;
import BackendSiadseUfps.siadse.entity.ContenidoMultimedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    // Método para listar todos los comentarios
    List<Comentario> findAll();

    // Método para listar los comentarios asignados a un archivo multimedia
    List<Comentario> findByContenidoMultimedia(ContenidoMultimedia contenidoMultimedia);

    // Método para listar todos los comentarios hechos por un usuario en diferentes documentos

}
