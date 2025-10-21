package com.shopstream.catalog.web;

import com.shopstream.catalog.doc.ProductDoc;
import com.shopstream.catalog.repo.ProductSearchRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    private final ProductSearchRepository repo;

    public CatalogController(ProductSearchRepository repo) { this.repo = repo; }

    @PostMapping("/index")
    public ProductDoc index(@RequestBody ProductDoc doc) {
        return repo.save(doc);
    }

    @GetMapping("/search")
    public List<ProductDoc> search(@RequestParam String q) {
        return repo.findByNameContainingIgnoreCase(q);
    }
}
