package de.daniel.ebookservice;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "ebook")
public class Ebook {
	
	private final int id;
	private final String title;


	public Ebook(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		return "Ebook [id=" + id + ", title=" + title + "]";
	}


}
