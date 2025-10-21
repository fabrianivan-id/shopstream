package com.shopstream.order.web;

import com.shopstream.common.events.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {
    private final KafkaTemplate<String, Object> kafka;
    public OrderController(KafkaTemplate<String, Object> kafka) { this.kafka = kafka; }

    @PostMapping("/orders")
    public Map<String, Object> create(@RequestBody Map<String, Object> req) {
        String orderId = UUID.randomUUID().toString();
        String customerId = (String) req.getOrDefault("customerId", "anonymous");
        String sku = (String) req.getOrDefault("sku", "SKU-001");
        int qty = (int) (req.getOrDefault("qty", 1) instanceof Integer i ? i : 1);
        var ev = new OrderCreatedEvent(orderId, customerId, sku, qty, Instant.now());
        kafka.send("orders.created", orderId, ev);
        return Map.of("status", "OK", "orderId", orderId);
    }
}
