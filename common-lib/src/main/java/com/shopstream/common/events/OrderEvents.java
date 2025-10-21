package com.shopstream.common.events;

import java.time.Instant;

public record OrderCreatedEvent(String orderId, String customerId, String sku, int qty, Instant createdAt) {}
public record PaymentAuthorizedEvent(String orderId, String paymentId, Instant authorizedAt) {}
public record InventoryReservedEvent(String orderId, String sku, int qty, Instant reservedAt) {}
