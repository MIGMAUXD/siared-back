package BackendSiadseUfps.siadse.service.implementations;
import BackendSiadseUfps.siadse.service.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class StorageServiceImplementation implements StorageService {

    private String rootLocation;
    @Override
    public void init() {
        try {
            Files.createDirectories(Paths.get(rootLocation));
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage location", e);
        }
    }
    @Override
    public String store(MultipartFile file, String folderPath, String fileName) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file");
        }
        Path folder = Paths.get(rootLocation, folderPath);
        Files.createDirectories(folder);
        Path filePath = folder.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        return fileName;
    }
    @Override
    public Resource loadAsResource(String fileName) throws IOException {
        Path file = Paths.get(rootLocation).resolve(fileName);
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Could not read file: " + fileName);
        }
    }
    @Override
    public void delete(String fileName) throws IOException {
        Path file = Paths.get(fileName);
        FileSystemUtils.deleteRecursively(file);
    }
    @Override
    public String getRootLocation() {
        return rootLocation;
    }
}