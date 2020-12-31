package de.daniel.ebookservice;

import java.nio.file.Path;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "ebook")
public class Ebook {
	
	public static class Author {
		private final String firstName;
		private final String lastName;

		public Author(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		@Override
		public String toString() {
			return "Author [firstName=" + firstName + ", lastName=" + lastName + "]";
		}
	}

	private final String id;
	private final List<String> titles;
	private final List<Author> authors;
	private final Path relattivePath;

	public Ebook(String id, List<String> titles, List<Author> authors, Path relativePath) {
		this.id = id;
		this.titles = titles;
		this.authors = authors;
		this.relattivePath = relativePath;
	}

	public String getId() {
		return id;
	}

	public List<String> getTitles() {
		return titles;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public Path getRelattivePath() {
		return relattivePath;
	}
	
	@Override
	public String toString() {
		return "Ebook [id=" + id + ", titles=" + titles + ", authors=" + authors + ", relativePath=" + relattivePath + "]";
	}


}
