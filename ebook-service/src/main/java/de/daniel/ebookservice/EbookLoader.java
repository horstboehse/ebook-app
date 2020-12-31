package de.daniel.ebookservice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Identifier;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;

@Component
public class EbookLoader {

	private static final Logger log = LoggerFactory.getLogger(EbookLoader.class);

	@Autowired
	private EbookRepository repository;

	@Value("${ebook.basePath}")
	private Path basePath;

	private EpubReader epubReader = new EpubReader();

	@PostConstruct
	private void load() {
		try (Stream<Path> ebooks = Files.walk(Path.of(URI.create("file:" + basePath)))) {
			ebooks.filter(this::isEpub).forEach(this::load);
		} catch (IOException e) {
			log.error("Error loading E-Books", e);
		}
	}

	private boolean isEpub(Path filePath) {
		return filePath.toFile().isFile() && filePath.toFile().getName().endsWith(".epub");
	}

	private void load(Path filePath) {		
		try (FileInputStream inputStream = new FileInputStream(filePath.toFile())) {
			Ebook ebook = loadEBook(filePath);
			//repository.save(ebook);
			log.info("Book: {}", ebook);
		} catch (IOException e) {
			log.error("Failed to load e-book from {}", filePath, e);
		} catch(Exception e) {
			log.error("Error", e);
		}
	}

	private Ebook loadEBook(Path filePath) throws IOException {
		Path relativePath = basePath.relativize(filePath);
		try(FileInputStream inputStream = new FileInputStream(filePath.toFile())) {			
			Book book = epubReader.readEpub(inputStream);
			checkBook(book, relativePath);
			return bookToEBook(relativePath, book);
		} 
	}

	
	private void checkBook(Book book, Path relativePath) {
		Metadata metadata = book.getMetadata();
		if(metadata.getIdentifiers().isEmpty()) {
			log.error("No Ideitifiers in {}", relativePath);
		}
		if(metadata.getAuthors().isEmpty()) {
			log.error("No Authors in {}", relativePath);
		}
		if(metadata.getTitles().isEmpty()) {
			log.error("No Titles in {}", relativePath);
		}
	}

	private Ebook bookToEBook(Path relativePath, Book book) {
		Ebook ebook = new Ebook(
			extractId(book),
			extractTitles(book),
			extractAuthors(book),
			relativePath);
		return ebook;
	}
	
	private String extractId(Book book) {
		List<Identifier> identifiers = book.getMetadata().getIdentifiers();
		Optional<Identifier> isbn = identifiers.stream()
			.filter(i -> i.getScheme().equalsIgnoreCase("ISBN"))
			.findAny();
		Identifier identifier = isbn.orElse(Identifier.getBookIdIdentifier(identifiers));
		return identifier != null ? identifier.getScheme() + ":" + identifier.getValue() : null;
	}

	private List<String> extractTitles(Book book) {
		return book.getMetadata().getTitles();
	}

	private List<Ebook.Author> extractAuthors(Book book) {
		return book.getMetadata().getAuthors().stream()
			.map(this::toAuthor)
			.collect(Collectors.toList());
	}

	private Ebook.Author toAuthor(Author author) {
		//The epub library mixes up last name and first name. So we have to switch it here.
		return new Ebook.Author(author.getLastname(), author.getFirstname());
	}

	

}
