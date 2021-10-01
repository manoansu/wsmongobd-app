package pt.amane.wsmongodb.models.dtos;

import java.io.Serializable;
import java.time.Instant;

import pt.amane.wsmongodb.models.entities.Post;

public class PostDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Instant moment;
	private String title;
	private String body;

	public PostDTO() {
	}

	public PostDTO(String id, Instant moment, String title, String body) {
		this.id = id;
		this.moment = moment;
		this.title = title;
		this.body = body;
	}

	public PostDTO(Post post) {
		this.id = post.getId();
		this.moment = post.getMoment();
		this.title = post.getTitle();
		this.body = post.getBody();
	}

	public String getId() {
		return id;
	}

	public Instant getMoment() {
		return moment;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
