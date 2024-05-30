package BackendSiadseUfps.siadse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BackendSiadseUfps.siadse.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findTop5ByOrderByFechaCreacion();

    Post findByUniqueTitleId(String uniqueTitleId);
}
