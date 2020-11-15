package de.daniel.ebookservice;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ebooks")
public class EbookAccessController {

	@Autowired
	private EbookRepository repository;

	@GetMapping(path = "/{id}") 
	public Ebook findOne(@PathVariable int id) {
		return repository.findById(id).orElseThrow();
	}

	@GetMapping(path = "")
	public Iterable<Ebook> find(@RequestParam Optional<String> title) {
		Iterable<Ebook> results = repository.findByTitle(title);
		return StreamSupport.stream(results.spliterator(), false).collect(Collectors.toList());
	}
	
}