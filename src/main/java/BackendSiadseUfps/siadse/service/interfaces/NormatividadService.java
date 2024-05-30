package BackendSiadseUfps.siadse.service.interfaces;

import BackendSiadseUfps.siadse.dto.ContenidoMutimediaDTO;
import BackendSiadseUfps.siadse.dto.NormatividadDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface NormatividadService {
    NormatividadDTO upload(Integer semilleroId, MultipartFile file) throws IOException;
}
