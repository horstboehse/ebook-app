package de.daniel.ebookservice;

import org.springframework.data.repository.CrudRepository;

public interface EbookRepository extends CrudRepository<Ebook, Integer> {
	
}
