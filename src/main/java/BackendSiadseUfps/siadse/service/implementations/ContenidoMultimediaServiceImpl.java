package BackendSiadseUfps.siadse.service.implementations;

import BackendSiadseUfps.siadse.dto.ContenidoMutimediaDTO;
import BackendSiadseUfps.siadse.entity.Album;
import BackendSiadseUfps.siadse.entity.ContenidoMultimedia;
import BackendSiadseUfps.siadse.repository.AlbumRepository;
import BackendSiadseUfps.siadse.repository.ContenidoMultimediaRepository;
import BackendSiadseUfps.siadse.service.interfaces.ContenidoMultimediaService;
import BackendSiadseUfps.siadse.service.interfaces.StorageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContenidoMultimediaServiceImpl implements ContenidoMultimediaService {
    @Autowired
    private ContenidoMultimediaRepository contenidoMultimediaRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private StorageService storageService;

    @Override
    public ContenidoMutimediaDTO createMediaContent(Integer albumId, String titulo, MultipartFile file) throws IOException {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el álbum con el ID especificado"));
        String albumFolderPath = album.getUbicacionAlbum();
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename().toString();
        storageService.store(file, albumFolderPath, fileName);
        String filePath = Paths.get(storageService.getRootLocation(), album.getUbicacionAlbum(), fileName).toString();
        ContenidoMultimedia contenidoMultimedia = new ContenidoMultimedia();
        contenidoMultimedia.setTitulo(titulo);
        contenidoMultimedia.setFechaSubida(new Date());
        contenidoMultimedia.setUrl(fileName);
        contenidoMultimedia.setFormato(obtenerFormatoArchivo(file.getOriginalFilename()));
        contenidoMultimedia.setAlbum(album);
        contenidoMultimedia.setRuta(filePath);
        album.setFechaActualizacion(new Date());
        albumRepository.save(album);
        ContenidoMultimedia savedContenidoMultimedia = contenidoMultimediaRepository.save(contenidoMultimedia);
        ContenidoMutimediaDTO responseDTO = new ContenidoMutimediaDTO();
        responseDTO.setAlbumId(albumId);
        BeanUtils.copyProperties(savedContenidoMultimedia, responseDTO);
        return responseDTO;
    }

    @Override
    public List<ContenidoMutimediaDTO> getMediaContentByAlbum(Integer albumId) {
        List<ContenidoMultimedia> contents = contenidoMultimediaRepository.findByAlbumId(albumId);
        return contents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMediaContent(Integer contenidoId) throws IOException {
        ContenidoMultimedia contenidoMultimedia = contenidoMultimediaRepository.findById(contenidoId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el contenido multimedia con el ID especificado"));

        storageService.delete(contenidoMultimedia.getRuta());

        contenidoMultimediaRepository.delete(contenidoMultimedia);
    }

    private ContenidoMutimediaDTO convertToDTO(ContenidoMultimedia contenido) {
        ContenidoMutimediaDTO dto = new ContenidoMutimediaDTO();
        BeanUtils.copyProperties(contenido, dto);
        dto.setAlbumId(contenido.getAlbum().getId());
        return dto;
    }

    private String obtenerFormatoArchivo(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            return fileName.substring(index + 1);
        }
        return "";
    }
}