package BackendSiadseUfps.siadse.service.interfaces;

import BackendSiadseUfps.siadse.dto.ComentarioDTO;

import java.util.List;

public interface ComentarioService {
    ComentarioDTO createComment(ComentarioDTO comentarioDTO, Integer mediaId);
    ComentarioDTO updateComment(ComentarioDTO comentarioDTO);
    void deleteComment(Integer comentarioId);
    List<ComentarioDTO> getAllComments();
    List<ComentarioDTO> getCommentsByMedia(Integer mediaId);
    List<ComentarioDTO> getCommentsByUser(Integer userId);
}
