
# Spring Boot RabbitMQ Microservice

This project is a simple **Java Spring Boot microservice** that demonstrates **asynchronous messaging** using **RabbitMQ**.

It includes:
- A REST API to receive order creation requests
- RabbitMQ messaging setup (exchange, queue, binding)
- Publishing JSON messages to RabbitMQ
- Listening and consuming messages from the queue

---

## 🏗 Project Structure

```
src/
└── main/
    └── java/
        └── com/
            └── example/
                └── orders/
                    ├── OrdersServiceApplication.java       # Main class
                    ├── config/
                    │   ├── RabbitConfig.java               # Declares queue/exchange/binding
                    │   └── RabbitTemplateConfig.java       # JSON message converter
                    ├── controller/
                    │   └── OrderController.java            # REST endpoint to post orders
                    ├── messaging/
                    │   ├── OrderPublisher.java             # Publishes messages to RabbitMQ
                    │   └── OrderCreatedListener.java       # Listens to queue messages
                    └── model/
                        └── OrderCreatedEvent.java          # DTO used for message payload
```

---

## ⚙️ Technologies

- Java 17
- Spring Boot
- RabbitMQ (Docker-based)
- Spring AMQP (RabbitTemplate, RabbitListener)
- Lombok
- Maven

---

## 🚀 How it works

1. You send a `POST /orders` HTTP request with JSON order data
2. The controller converts the request into an `OrderCreatedEvent` object
3. The message is published to RabbitMQ exchange `orders.exchange`
4. RabbitMQ routes the message to `orders.created.q` via routing key `order.created`
5. A listener consumes the message and logs it

---

## 🔁 Sample CURL Command

```bash
curl -X POST "http://localhost:8080/orders" -H "Content-Type: application/json" -d '{"orderId":"o-1001","customerId":"c-42","amount":99.99}'
```

---

## 🐳 Running RabbitMQ via Docker

Create a file called `docker-compose.yml` in your project root:

```yaml
version: "3.9"
services:
  rabbit:
    image: rabbitmq:3.13-management
    ports:
      - "5672:5672"
      - "15672:15672"
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest
```

Then start RabbitMQ locally:

```bash
docker compose up -d
```

Visit the RabbitMQ UI at: [http://localhost:15672](http://localhost:15672) (login: `guest` / `guest`)

---

## ✅ Next Steps

- [ ] Add DLQ and retry config
- [ ] Add Dockerfile for containerizing the app
- [ ] Create second microservice to consume messages
- [ ] Add CI/CD with GitHub Actions
- [ ] Integration testing with Testcontainers

---

## 📄 License

This project is for learning and demonstration purposes.
