package com.shopstream.inventory.api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
  private final InventoryService svc;
  @GetMapping("/{sku}")
  public Integer get(@PathVariable String sku) { return svc.getStock(sku); }
  @PostMapping("/{sku}")
  public Integer set(@PathVariable String sku, @RequestParam int value) { svc.setStock(sku, value); return value; }
}
