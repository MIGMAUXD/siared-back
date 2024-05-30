package BackendSiadseUfps.siadse.service.interfaces;

import java.util.List;

import BackendSiadseUfps.siadse.dto.CreatePostRequestDTO;
import BackendSiadseUfps.siadse.dto.RecentPostDTO;
import BackendSiadseUfps.siadse.dto.PostShowDTO;
import BackendSiadseUfps.siadse.dto.PostDTO;
import BackendSiadseUfps.siadse.entity.Post;

public interface IPostService {

    // Listar los 5 post mas recientes 
    List<RecentPostDTO> getRecentPost();

    //Listar todos los post 
    List<PostDTO> getAllPost();

    PostShowDTO getPostInfoByShow(String uniqueTitleId);

    Post createPost(CreatePostRequestDTO newPostDTO);

}
