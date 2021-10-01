package pt.amane.wsmongodb.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import pt.amane.wsmongodb.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> serachTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);

}
