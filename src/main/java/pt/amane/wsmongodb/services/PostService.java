package pt.amane.wsmongodb.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.wsmongodb.models.dtos.PostDTO;
import pt.amane.wsmongodb.models.entities.Post;
import pt.amane.wsmongodb.models.entities.User;
import pt.amane.wsmongodb.repositories.PostRepository;
import pt.amane.wsmongodb.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;

	@Transactional(readOnly = true)
	public PostDTO findById(String id) {
		Optional<Post> postId = repository.findById(id);
		Post post = postId
				.orElseThrow(() -> new ObjectNotFoundException("Id not found! Id: " + id + User.class.getName()));
		return new PostDTO(post);
	}

	@Transactional(readOnly = true)
	public List<PostDTO> findAll(String text) {
		List<Post> lisUser = repository.findByTitleContainingIgnoreCase(text);
		return lisUser.stream().map(dto -> new PostDTO(dto)).collect(Collectors.toList());
	}


}
