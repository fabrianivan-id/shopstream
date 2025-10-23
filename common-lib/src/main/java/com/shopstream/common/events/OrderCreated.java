package com.shopstream.common.events;
public record OrderCreated(String orderId, String customerId, String sku, int qty, long ts) {}
