package BackendSiadseUfps.siadse.service.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendSiadseUfps.siadse.dto.ComentarioDTO;
import BackendSiadseUfps.siadse.entity.Comentario;
import BackendSiadseUfps.siadse.repository.ComentarioRepository;
import BackendSiadseUfps.siadse.service.interfaces.ComentarioService;


@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    


    @Override
    public ComentarioDTO createComment(ComentarioDTO comentarioDTO, Integer mediaId) {
        return null;
    }

    @Override
    public ComentarioDTO updateComment(ComentarioDTO comentarioDTO) {
        Comentario existingComentario = comentarioRepository.findById(comentarioDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el comentario con el ID especificado"));

        existingComentario.setComentario(comentarioDTO.getComentario());
        existingComentario.setFechaCreacion(new Date());
        Comentario updatedComentario = comentarioRepository.save(existingComentario);

        ComentarioDTO responseDTO = new ComentarioDTO();
        responseDTO.setContenidoMultimediaId(updatedComentario.getContenidoMultimedia().getId());
        BeanUtils.copyProperties(updatedComentario, responseDTO);

        return responseDTO;
    }

    @Override
    public void deleteComment(Integer comentarioId) {
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el comentario con el ID especificado"));

        comentarioRepository.delete(comentario);
    }

    @Override
    public List<ComentarioDTO> getAllComments() {
        return null;
    }

    @Override
    public List<ComentarioDTO> getCommentsByMedia(Integer mediaId) {
        return null;
    }

    @Override
    public List<ComentarioDTO> getCommentsByUser(Integer userId) {
        return null;
    }





}