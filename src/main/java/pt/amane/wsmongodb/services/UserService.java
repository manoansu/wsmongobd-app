package pt.amane.wsmongodb.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.wsmongodb.models.dtos.PostDTO;
import pt.amane.wsmongodb.models.dtos.UserDTO;
import pt.amane.wsmongodb.models.entities.User;
import pt.amane.wsmongodb.repositories.UserRepository;
import pt.amane.wsmongodb.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Transactional(readOnly = true)
	public UserDTO findById(String id) {
		Optional<User> userId = repository.findById(id);
		User user = userId
				.orElseThrow(() -> new ObjectNotFoundException("Id not found! Id: " + id + User.class.getName()));
		return new UserDTO(user);
	}

	@Transactional(readOnly = true)
	public List<UserDTO> findAll() {
		List<User> lisUser = repository.findAll();
		return lisUser.stream().map(dto -> new UserDTO(dto)).collect(Collectors.toList());
	}

	@Transactional
	public UserDTO create(UserDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user = repository.save(user);
		return new UserDTO(user);
	}

	@Transactional
	public UserDTO update(String id, UserDTO dto) {
		User user = getEntityById(id);
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user = repository.save(user);
		return new UserDTO(user);
	}

	public void delete(String id) {
		User user = getEntityById(id);
		repository.deleteById(user.getId());
	}

	private User getEntityById(String id) {
		Optional<User> userId = repository.findById(id);
		return userId.orElseThrow(() -> new ObjectNotFoundException("Id not found! Id: " + id + User.class.getName()));

	}

	public List<PostDTO> getUsersPost(String id) {
		User user = getEntityById(id);
		return user.getPosts().stream().map(p -> new PostDTO(p)).collect(Collectors.toList());
	}

}
