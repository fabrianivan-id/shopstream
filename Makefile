.PHONY: up down logs build run all reset topics smoke

up:
	docker compose up -d

down:
	docker compose down -v

logs:
	docker compose logs -f

build:
	./gradlew clean build -x test

run:
	./gradlew :api-gateway:bootRun & \
	./gradlew :product-service:bootRun & \
	./gradlew :order-service:bootRun & \
	./gradlew :inventory-service:bootRun & \
	./gradlew :catalog-service:bootRun & \
	./gradlew :stream-processor:bootRun

topics:
	docker exec -it $$(docker ps -qf "name=kafka") bash -lc \
	'kafka-topics --create --if-not-exists --bootstrap-server localhost:9092 --topic orders.created && \
	 kafka-topics --create --if-not-exists --bootstrap-server localhost:9092 --topic orders.enriched && \
	 kafka-topics --list --bootstrap-server localhost:9092'

smoke:
	# product top
	curl -s "http://localhost:8080/products/top?perCategory=2" | jq . ; \
	# order publish
	curl -s -X POST "http://localhost:8080/orders" \
	  -H "Content-Type: application/json" \
	  -d '{"customerId":"c1","sku":"SKU-001","qty":2}' | jq . ; \
	# redis inv
	curl -s "http://localhost:8080/inventory/SKU-001" ; echo ; \
	curl -s -X POST "http://localhost:8080/inventory/SKU-001?value=10" ; echo ; \
	# es index+search
	curl -s -X POST "http://localhost:8080/catalog/index" \
	  -H "Content-Type: application/json" \
	  -d '{"sku":"SKU-002","name":"Mechanical Keyboard","category":"accessories","price":45.00}' | jq . ; \
	curl -s "http://localhost:8080/catalog/search?q=keyboard" | jq .

all: up build topics run
reset: down up build topics
