package com.shopstream.product.web;

import com.shopstream.product.model.Product;
import com.shopstream.product.repo.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private final ProductRepository repo;
    public ProductController(ProductRepository repo) { this.repo = repo; }

    @GetMapping("/products/top")
    public Map<String, List<Product>> topByRevenue(@RequestParam(defaultValue = "3") int perCategory) {
        // Java Stream: group & sort in-memory for demo
        return repo.topProductsByCategoryRevenue(perCategory).stream()
                .collect(Collectors.groupingBy(Product::category,
                        Collectors.collectingAndThen(Collectors.toList(), list ->
                                list.stream().sorted(Comparator.comparing(Product::price).reversed()).toList()
                        )));
    }

    @GetMapping("/products/search")
    public List<Product> search(@RequestParam String q) {
        return repo.search(q);
    }

    @GetMapping("/products/ping/{msg}")
    public String ping(@PathVariable String msg) {
        return "product-service: " + msg;
    }
}
