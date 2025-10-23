package com.shopstream.product.api;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.*;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
  private final JdbcTemplate jdbc;
  @GetMapping("/top")
  public Map<String,List<Map<String,Object>>> top(@RequestParam(defaultValue="2") int perCategory) {
    var rows = jdbc.queryForList("SELECT sku, name, category, price FROM products");
    return rows.stream()
        .collect(Collectors.groupingBy(r -> (String) r.get("category")))
        .entrySet().stream()
        .collect(Collectors.toMap(
          Map.Entry::getKey,
          e -> e.getValue().stream()
                  .sorted(Comparator.<Map<String,Object>>comparingDouble(r -> ((Number)r.get("price")).doubleValue()).reversed())
                  .limit(perCategory)
                  .toList()
        ));
  }
}
