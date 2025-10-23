# ShopStream Fix Pack

Extract at the repo root, then:

```bash
docker compose up -d
./gradlew clean build
make topics
make run
```

Key endpoints:
- Gateway health: `http://localhost:8080/actuator/health`
- Product Top: `GET http://localhost:8080/products/top?perCategory=2`
- Create Order: `POST http://localhost:8080/orders` body `{"customerId":"c1","sku":"SKU-001","qty":2}`
- Inventory: `GET/POST http://localhost:8080/inventory/{sku}`
- Catalog Search: `GET http://localhost:8080/catalog/search?q=keyboard`

Swagger UIs:
- Product: `http://localhost:8081/swagger`
- Order: `http://localhost:8082/swagger`
- Inventory: `http://localhost:8083/swagger`
- Catalog: `http://localhost:8084/swagger`
