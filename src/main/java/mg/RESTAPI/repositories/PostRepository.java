package mg.RESTAPI.repositories;

import mg.RESTAPI.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
