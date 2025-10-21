package com.shopstream.catalog.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "products")
public class ProductDoc {
    @Id
    private String sku;
    private String name;
    private String category;
    private double price;
    public ProductDoc() {}
    public ProductDoc(String sku, String name, String category, double price) {
        this.sku = sku; this.name = name; this.category = category; this.price = price;
    }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
}
