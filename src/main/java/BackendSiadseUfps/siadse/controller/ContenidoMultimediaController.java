package BackendSiadseUfps.siadse.controller;

import BackendSiadseUfps.siadse.dto.ContenidoMutimediaDTO;
import BackendSiadseUfps.siadse.dto.ResponseDTO;
import BackendSiadseUfps.siadse.service.interfaces.ContenidoMultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/public/media")
public class ContenidoMultimediaController {

    @Autowired
    private ContenidoMultimediaService contenidoMultimediaService;

    @PostMapping("/upload")
    public ResponseEntity<ContenidoMutimediaDTO> createMediaContent(@RequestParam Integer albumId,
                                                                    @RequestParam String titulo,
                                                                    @RequestParam("file") MultipartFile file) throws IOException {
        ContenidoMutimediaDTO createdMediaContent = contenidoMultimediaService.createMediaContent(albumId, titulo, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMediaContent);
    }

    @GetMapping("/album")
    public ResponseEntity<List<ContenidoMutimediaDTO>> getMediaContentByAlbum(@RequestParam Integer albumId) {
        List<ContenidoMutimediaDTO> mediaContent = contenidoMultimediaService.getMediaContentByAlbum(albumId);
        return ResponseEntity.ok(mediaContent);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteMediaContent(@RequestParam Integer contentId) throws IOException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            contenidoMultimediaService.deleteMediaContent(contentId);
            responseDTO.setMessage("Contenido multimedia eliminado correctamente");
            return ResponseEntity.ok(responseDTO);
        } catch (IOException e) {
            responseDTO.setMessage("Error al eliminar el contenido multimedia: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}
