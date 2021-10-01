package pt.amane.wsmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import pt.amane.wsmongodb.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
