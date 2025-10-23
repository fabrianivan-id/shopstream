package com.shopstream.order.api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopstream.common.events.OrderCreated;
import com.shopstream.common.topics.Topics;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
  private final KafkaTemplate<String, String> kafka;
  private final ObjectMapper om;
  @Value("${app.topics.ordersCreated:" + "orders.created" + "}")
  String topic;
  public record CreateOrder(String customerId, String sku, int qty) {}
  @PostMapping
  public Map<String,Object> create(@RequestBody CreateOrder req) throws JsonProcessingException {
    var event = new OrderCreated(UUID.randomUUID().toString(), req.customerId(), req.sku(), req.qty(), System.currentTimeMillis());
    kafka.send(topic, event.sku(), om.writeValueAsString(event));
    return Map.of("status","ok","publishedTo",topic,"sku",event.sku());
  }
}
