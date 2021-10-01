package pt.amane.wsmongodb.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import pt.amane.wsmongodb.models.entities.User;
import pt.amane.wsmongodb.repositories.PostRepository;
import pt.amane.wsmongodb.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@PostConstruct // help to load data in browser..
	public void init() {
		// delete all data in db before start..
		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User carla = new User(null, "Carla Esteves", "carla@outlook.com");
		User katia = new User(null, "katia Brandão de Oliveira", "katiaob@gmail.com");
		User carlos = new User(null, "Carlos de Barros", "carlosb@yahoo.com");
		User julia = new User(null, "Julia Pinheiro", "juliap@hotmail.com");
		User edson = new User(null, "Edson Celulari", "edsonc@live.com");

		userRepository.saveAll(Arrays.asList(maria, carla, carlos, julia, katia, edson));
	}
}
