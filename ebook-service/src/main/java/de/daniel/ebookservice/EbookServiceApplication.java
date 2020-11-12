package de.daniel.ebookservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EbookServiceApplication implements CommandLineRunner{

	@Autowired
	private EbookRepository repository;

	private Logger logger = LoggerFactory.getLogger(EbookServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EbookServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		repository.save(new Ebook(5, "Paradox"));
		repository.save(new Ebook(6, "Brave New World"));

		Iterable<Ebook> storedBooks = repository.findAll();

		logger.info("Stored books: {}", storedBooks);


	}

}
