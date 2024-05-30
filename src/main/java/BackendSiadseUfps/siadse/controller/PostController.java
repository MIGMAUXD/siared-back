package BackendSiadseUfps.siadse.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import BackendSiadseUfps.siadse.service.implementations.PostServiceImpl;
import BackendSiadseUfps.siadse.entity.Post;
import BackendSiadseUfps.siadse.dto.CreatePostRequestDTO;
import BackendSiadseUfps.siadse.dto.PostDTO;
import BackendSiadseUfps.siadse.dto.RecentPostDTO;
import BackendSiadseUfps.siadse.dto.PostShowDTO;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostServiceImpl postServiceImpl;

    @GetMapping
    public List<PostDTO> getAllPost(){
        return postServiceImpl.getAllPost();
    }

    @Transactional
    @GetMapping("/title/{uniqueTitleId}")
    public PostShowDTO getRecentPost(@PathVariable String uniqueTitleId){
        PostShowDTO postShowDTO = postServiceImpl.getPostInfoByShow(uniqueTitleId);
        return postShowDTO != null ? postShowDTO : null;
    }

    @Transactional
    @GetMapping("/recent")
    public List<RecentPostDTO> getRecentPost(){
        return postServiceImpl.getRecentPost();
    }


    @PostMapping("create")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostRequestDTO postRequestDTO){
        try {
            Post post = postServiceImpl.createPost(postRequestDTO);
            if(post != null)
               return ResponseEntity.ok(post);
        } catch (IllegalArgumentException e) {

        }
        return ResponseEntity.ok(null);
    }

}
