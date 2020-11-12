package de.daniel.ebookservice;

public class Ebook {
	
	private final int id;
	private final String name;


	public Ebook(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Ebook [id=" + id + ", name=" + name + "]";
	}


}
