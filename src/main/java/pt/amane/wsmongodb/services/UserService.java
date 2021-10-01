package pt.amane.wsmongodb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.wsmongodb.models.dtos.UserDTO;
import pt.amane.wsmongodb.models.entities.User;
import pt.amane.wsmongodb.repositories.UserRepository;
import pt.amane.wsmongodb.services.exceptions.ObjectNotFoundException;

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

}
