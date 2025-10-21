package com.shopstream.catalog.repo;

import com.shopstream.catalog.doc.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductDoc, String> {
    List<ProductDoc> findByNameContainingIgnoreCase(String name);
    List<ProductDoc> findByCategory(String category);
}
