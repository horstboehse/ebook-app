package de.daniel.ebookservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EbookAccessController {

	@GetMapping(path = "/{id}") 
	public Ebook findOne(@PathVariable int id) {
		return new Ebook(id);
	}
	
}