package de.daniel.ebookservice;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EbookRepository extends ElasticsearchRepository<Ebook, Integer> {
	
	Iterable<Ebook> findByTitle(String title);

}
