package pt.amane.wsmongodb.services;

import java.time.Instant;
import java.time.format.DateTimeParseException;
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
	
	
	public List<PostDTO> fullSearch(String text, String start, String end) {
		Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
		Instant endMoment = convertMoment(start, Instant.now());
		List<Post> lisUser = repository.fullSearch(text, startMoment, endMoment);
		return lisUser.stream().map(dto -> new PostDTO(dto)).collect(Collectors.toList());
	}

	private Instant convertMoment(String OriginStart, Instant alternative) {
		try {
			return Instant.parse(OriginStart);
		} catch (DateTimeParseException e) {
			return alternative;
		}
	}



}
