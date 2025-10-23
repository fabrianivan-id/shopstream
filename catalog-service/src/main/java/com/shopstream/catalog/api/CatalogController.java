package com.shopstream.catalog.api;
import com.shopstream.catalog.model.ProductDoc;
import com.shopstream.catalog.repo.ProductIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {
  private final ProductIndex index;
  @PostMapping("/index")
  public ProductDoc index(@RequestBody ProductDoc doc) { return index.save(doc); }
  @GetMapping("/search")
  public List<ProductDoc> search(@RequestParam String q) { return index.findByNameContainingIgnoreCase(q); }
}
