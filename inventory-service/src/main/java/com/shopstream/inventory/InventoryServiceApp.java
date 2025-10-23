package com.shopstream.inventory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching
@SpringBootApplication
public class InventoryServiceApp {
  public static void main(String[] args) { SpringApplication.run(InventoryServiceApp.class, args); }
}
