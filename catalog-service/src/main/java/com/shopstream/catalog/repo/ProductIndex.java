package com.shopstream.catalog.repo;
import com.shopstream.catalog.model.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;
public interface ProductIndex extends ElasticsearchRepository<ProductDoc, String> {
  List<ProductDoc> findByNameContainingIgnoreCase(String q);
}
