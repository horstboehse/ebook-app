package de.daniel.ebookservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EbookAccessController {

	@Autowired
	private EbookRepository repository;

	@GetMapping(path = "/{id}") 
	public Ebook findOne(@PathVariable int id) {
		return repository.findById(id).orElseThrow();
	}
	
}