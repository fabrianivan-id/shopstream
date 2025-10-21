# Shopstream (Mini Project)

**What this demonstrates**  
- Spring IoC  
- Java Stream API  
- **Advanced Native SQL** (CTE + window functions) in Postgres  
- Containerization & microservices (Docker + Compose)  
- Kafka (Producer) & **Kafka Streams** (Processor)  
- Redis caching (as simple data grid)  
- Elasticsearch (+ Kibana) for search  

## Modules
- `api-gateway` — Spring Cloud Gateway routing to services.
- `product-service` — Postgres + advanced native SQL endpoints. Uses Java Streams in controller.
- `order-service` — Exposes `/orders` to publish `orders.created` to Kafka.
- `inventory-service` — Redis cache with `@Cacheable` & `@CacheEvict`.
- `catalog-service` — Elasticsearch index/search for products.
- `stream-processor` — Kafka Streams topology reading `orders.created` → `orders.enriched`.

## Infra
```bash
docker compose up -d
```

## Build
```bash
./gradlew clean build
```

## Run (dev)
```bash
./gradlew :api-gateway:bootRun
./gradlew :product-service:bootRun
./gradlew :order-service:bootRun
./gradlew :inventory-service:bootRun
./gradlew :catalog-service:bootRun
./gradlew :stream-processor:bootRun
```

## Quick checks
- `GET http://localhost:8080/products/top?perCategory=2`
- `POST http://localhost:8080/orders` body: `{"customerId":"c1","sku":"SKU-001","qty":2}`
- `GET http://localhost:8080/inventory/SKU-001`
- `POST http://localhost:8080/inventory/SKU-001?value=10`
- `POST http://localhost:8080/catalog/index` body: `{"sku":"SKU-001","name":"Wireless Mouse","category":"accessories","price":15.99}`
- `GET http://localhost:8080/catalog/search?q=mouse`

> Note: The repo intentionally avoids restricted keywords per assignment instructions.
