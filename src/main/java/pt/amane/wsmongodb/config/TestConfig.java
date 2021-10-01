package pt.amane.wsmongodb.config;

import java.time.Instant;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import pt.amane.wsmongodb.models.embedded.Author;
import pt.amane.wsmongodb.models.embedded.Comment;
import pt.amane.wsmongodb.models.entities.Post;
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
		
		Post post1 = new Post(null, Instant.parse("2021-02-13T11:15:01Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new Author(maria));
		Post post2 = new Post(null, Instant.parse("2021-02-14T10:05:49Z"), "Bom dia", "Acordei feliz hoje!", new Author(maria));
		Post post3 = new Post(null, Instant.parse("2021-02-13T11:15:01Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new Author(julia));
		Post post4 = new Post(null, Instant.parse("2021-02-14T10:05:49Z"), "Bom dia", "Acordei feliz hoje!", new Author(carlos));
		Post post5 = new Post(null, Instant.parse("2021-02-13T11:15:01Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new Author(katia));
		Post post6 = new Post(null, Instant.parse("2021-02-14T10:05:49Z"), "Bom dia", "Acordei feliz hoje!", new Author(edson));

		
		Comment c1 = new Comment(null, "Boa viagem mano!", Instant.parse("2021-02-13T14:30:01Z"), new Author(julia));
		Comment c2 = new Comment(null, "Aproveite", Instant.parse("2021-02-13T15:38:05Z"), new Author(maria));
		Comment c3 = new Comment(null, "Tenha um ótimo dia!", Instant.parse("2021-02-14T12:34:26Z"), new Author(carlos));
		Comment c4 = new Comment(null, "Boa viagem mano!", Instant.parse("2021-02-13T14:30:01Z"), new Author(carla));
		Comment c5 = new Comment(null, "Aproveite", Instant.parse("2021-02-13T15:38:05Z"), new Author(katia));
		Comment c6 = new Comment(null, "Tenha um ótimo dia!", Instant.parse("2021-02-14T12:34:26Z"), new Author(julia));
		Comment c7 = new Comment(null, "Boa viagem mano!", Instant.parse("2021-02-13T14:30:01Z"), new Author(edson));
		Comment c8 = new Comment(null, "Aproveite", Instant.parse("2021-02-13T15:38:05Z"), new Author(carla));
		Comment c9 = new Comment(null, "Tenha um ótimo dia!", Instant.parse("2021-02-14T12:34:26Z"), new Author(katia));
		
		post1.getComments().addAll(Arrays.asList(c1,c5));
		post2.getComments().addAll(Arrays.asList(c3,c4));
		post6.getComments().addAll(Arrays.asList(c9,c7));
		post3.getComments().addAll(Arrays.asList(c9,c2));
		post5.getComments().addAll(Arrays.asList(c6));
		post4.getComments().addAll(Arrays.asList(c8));
		
		postRepository.saveAll(Arrays.asList(post1,post2,post3,post4,post5,post6));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		julia.getPosts().addAll(Arrays.asList(post3));
		carlos.getPosts().addAll(Arrays.asList(post4));
		katia.getPosts().addAll(Arrays.asList(post5));
		edson.getPosts().addAll(Arrays.asList(post6));
		
		userRepository.saveAll(Arrays.asList(maria, julia, katia, carlos, edson));
	}
}
