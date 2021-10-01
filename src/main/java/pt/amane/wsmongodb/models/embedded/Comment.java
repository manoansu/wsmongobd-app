package pt.amane.wsmongodb.models.embedded;

import java.time.Instant;

public class Comment {

	private String id;
	private String text;
	private Instant moment;

	private Author author;

	public Comment() {
	}

	public Comment(String id, String text, Instant moment, Author author) {
		this.id = id;
		this.text = text;
		this.moment = moment;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public Instant getMoment() {
		return moment;
	}

	public Author getAuthor() {
		return author;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
