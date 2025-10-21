package com.shopstream.inventory.web;

import com.shopstream.inventory.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class InventoryController {
    private final StockService stock;
    public InventoryController(StockService stock) { this.stock = stock; }

    @GetMapping("/inventory/{sku}")
    public Map<String, Object> get(@PathVariable String sku) {
        return Map.of("sku", sku, "stock", stock.getStock(sku));
    }

    @PostMapping("/inventory/{sku}")
    public Map<String, Object> set(@PathVariable String sku, @RequestParam int value) {
        return Map.of("sku", sku, "stock", stock.setStock(sku, value));
    }
}
