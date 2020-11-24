package de.daniel.ebookservice;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EbookLoader {

	private static final Logger log = LoggerFactory.getLogger(EbookLoader.class);

	@Autowired
	private EbookRepository repository;

	@Value("${ebook.basePath}")
	private String basePath;

	@PostConstruct
	private void load() {
		try(Stream<Path> ebooks = Files.walk(Path.of(URI.create("file:" + basePath)))) {
			ebooks
				.filter(this::isEpub)
				.forEach(this::load);
		} catch(IOException e) {
			log.error("Error loading E-Books", e);
		}
	}

	private boolean isEpub(Path filePath) {
		return filePath.toFile().isFile() && filePath.toFile().getName().endsWith(".epub");
	}

	private void load(Path filePath) {
		log.info(filePath.toString());
	}

}
