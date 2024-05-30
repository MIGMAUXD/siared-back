package BackendSiadseUfps.siadse.service.implementations;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.databasemigrationservice.model.PostgreSqlDataProviderSettings;

import BackendSiadseUfps.siadse.dto.CreatePostRequestDTO;
import BackendSiadseUfps.siadse.dto.PostShowDTO;
import BackendSiadseUfps.siadse.dto.RecentPostDTO;
import BackendSiadseUfps.siadse.entity.Post;
import BackendSiadseUfps.siadse.repository.PostRepository;
import BackendSiadseUfps.siadse.service.interfaces.IPostService;
import BackendSiadseUfps.siadse.dto.PostDTO;

@Service
public class PostServiceImpl  implements IPostService{

    @Autowired
    private  PostRepository postRepository;

    @Override
    public List<RecentPostDTO> getRecentPost() {
        return postRepository.findTop5ByOrderByFechaCreacion().stream().map(post -> {
            return RecentPostDTO.builder()
            .titulo(post.getTitulo())
            .fechaCreacion(post.getFechaCreacion())
            .tag(post.getTag())
            .imagenEncabezado(post.getImagenEncabezado())
            .uniqueTitleId(post.getUniqueTitleId())
            .build();
        }).collect(Collectors.toList());
    }


    /**
     * Metodo para crear un nuevo post 
     * Args: 
     *  CreatePostRequestDTO newPostRequestDTO corresponde a un dto para crear un nuevo post.
     */
    @Override
    public Post createPost(CreatePostRequestDTO newPostRequestDTO) {
        
        Post post = new Post();

        post.setTitulo(newPostRequestDTO.getTitulo());
        post.setContenido( newPostRequestDTO.getContenido());
        post.setFechaCreacion(new Date());
        post.setTag(newPostRequestDTO.getTag());
        post.setEncabezados(newPostRequestDTO.getEncabezados());
        post.setImagenEncabezado(newPostRequestDTO.getImagenEncabezado());
        post.setUniqueTitleId( newPostRequestDTO.getTitulo().replace(" ", "-"));
        
        return postRepository.save(post);
    }

    /**
     * Obtiene todos los post registrados a la fecha.
     */
    @Override
    public List<PostDTO> getAllPost(){
        return postRepository.findAll().stream().map(post -> {
            return PostDTO.builder()
                    .id(post.getId())
                    .titulo(post.getTitulo())
                    .fechaCreacion(post.getFechaCreacion())
                    .link(post.getUniqueTitleId())
                    .imagen(post.getImagenEncabezado())
                    .tag(post.getTag())
                    .build();
        }).collect(Collectors.toList());
    }


    /**
     * Obtiene la informacion a mostrar en el cliente a traves del uniqueTitleId
     */
    @Override
    public PostShowDTO getPostInfoByShow(String uniqueTitleId) {
        Post post = postRepository.findByUniqueTitleId(uniqueTitleId);

        if (post == null)
            throw new Error();
        
        return PostShowDTO.builder()
                .encabezados(post.getEncabezados().split("~"))
                .contenidos(post.getContenido().split("~"))
                .imagenEncabezado(post.getImagenEncabezado())
                .tag(post.getTag())
                .build();
    }
    
}
