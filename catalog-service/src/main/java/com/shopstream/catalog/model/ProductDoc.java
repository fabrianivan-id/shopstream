package com.shopstream.catalog.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
@Document(indexName = "products")
public record ProductDoc(@Id String sku, String name, String category, double price) {}
