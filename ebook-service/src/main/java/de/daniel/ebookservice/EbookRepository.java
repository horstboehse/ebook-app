package de.daniel.ebookservice;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EbookRepository extends ElasticsearchRepository<Ebook, Integer> {
	
}
