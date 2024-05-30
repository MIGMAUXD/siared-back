package BackendSiadseUfps.siadse.controller;

import BackendSiadseUfps.siadse.dto.AlbumDTO;
import BackendSiadseUfps.siadse.service.interfaces.AlbumService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/public/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO) {
        AlbumDTO createdAlbum = albumService.createAlbum(albumDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlbum);
    }

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAlbums(){
        return ResponseEntity.ok(
                albumService.getAlbums().stream().map(album -> {
                    AlbumDTO albumDTO = new AlbumDTO();
                    BeanUtils.copyProperties(album, albumDTO);
                    return albumDTO;
                }).collect(Collectors.toList())
        );

    }

    @DeleteMapping
    public ResponseEntity<String> deleteAlbum(@RequestParam Integer albumId) {
        try {
            albumService.deleteAlbum(albumId);
            return new ResponseEntity<>("Álbum eliminado correctamente", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el álbum", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
