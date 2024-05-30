package BackendSiadseUfps.siadse.service.interfaces;

import BackendSiadseUfps.siadse.dto.ContenidoMutimediaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ContenidoMultimediaService {
    ContenidoMutimediaDTO createMediaContent(Integer albumId, String titulo, MultipartFile file) throws IOException;
    List<ContenidoMutimediaDTO> getMediaContentByAlbum(Integer albumId);
    void deleteMediaContent(Integer contenidoId) throws IOException;
}
