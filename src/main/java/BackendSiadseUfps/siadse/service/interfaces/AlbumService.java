package BackendSiadseUfps.siadse.service.interfaces;

import BackendSiadseUfps.siadse.dto.AlbumDTO;
import BackendSiadseUfps.siadse.entity.Album;

import java.util.List;

public interface AlbumService {

    public AlbumDTO createAlbum (AlbumDTO albumDTO);

    List<Album> getAlbums();
    void deleteAlbum(Integer albumId);
}
