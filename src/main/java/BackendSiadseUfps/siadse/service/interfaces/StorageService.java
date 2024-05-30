package BackendSiadseUfps.siadse.service.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    void init();
    String store(MultipartFile file, String folderPath, String fileName) throws IOException;
    Resource loadAsResource(String fileName) throws IOException;

    void delete(String fileName) throws IOException;

    String getRootLocation();

}
