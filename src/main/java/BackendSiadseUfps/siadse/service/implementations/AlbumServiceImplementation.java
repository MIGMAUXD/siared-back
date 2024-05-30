package BackendSiadseUfps.siadse.service.implementations;
import BackendSiadseUfps.siadse.dto.AlbumDTO;
import BackendSiadseUfps.siadse.entity.Album;
import BackendSiadseUfps.siadse.entity.ContenidoMultimedia;
import BackendSiadseUfps.siadse.repository.AlbumRepository;
import BackendSiadseUfps.siadse.repository.ContenidoMultimediaRepository;
import BackendSiadseUfps.siadse.service.interfaces.AlbumService;
import BackendSiadseUfps.siadse.service.interfaces.StorageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImplementation implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ContenidoMultimediaRepository contenidoMultimediaRepository;
    @Override
    public AlbumDTO createAlbum(AlbumDTO albumDTO) {
        Album album = new Album();
        BeanUtils.copyProperties(albumDTO, album);
        album.setFechaCreacion(new Date());
        album.setFechaActualizacion(new Date());
        String albumFolderName = albumDTO.getTitulo();
        String albumFolderPath = Paths.get(storageService.getRootLocation(), albumFolderName).toString();
        try {
            Files.createDirectories(Paths.get(albumFolderPath));
        } catch (IOException e) {
            throw new RuntimeException("Error, No se creó el álbum", e);
        }
        album.setUbicacionAlbum(albumFolderName);
        album.setRuta(albumFolderPath);
        album = albumRepository.save(album);
        AlbumDTO responseDTO = new AlbumDTO();
        BeanUtils.copyProperties(album, responseDTO);
        return responseDTO;
    }
    @Override
    public List<Album> getAlbums() {
        return (List<Album>) albumRepository.findAll();
    }

    @Override
    public void deleteAlbum(Integer albumId) {
        Optional<Album> optionalAlbum = albumRepository.findById(albumId);
        if (optionalAlbum.isPresent()) {
            Album album = optionalAlbum.get();
            String albumFolderPath = album.getRuta();

            List<ContenidoMultimedia> multimediaList = contenidoMultimediaRepository.findByAlbumId(albumId);
            for (ContenidoMultimedia multimedia : multimediaList) {
                try {
                    storageService.delete(multimedia.getRuta());
                } catch (IOException e) {
                    throw new RuntimeException("Error al eliminar archivo multimedia asociado al álbum", e);
                }
                contenidoMultimediaRepository.delete(multimedia);
            }


            try {
                storageService.delete(albumFolderPath);
            } catch (IOException e) {
                throw new RuntimeException("Error al eliminar carpeta del álbum", e);
            }
            albumRepository.delete(album);
        } else {
            throw new IllegalArgumentException("No se encontró el álbum con el ID especificado");
        }
    }
}