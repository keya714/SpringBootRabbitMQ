package com.example.orders.controller;

import com.example.orders.messaging.OrderPublisher;
import com.example.orders.model.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderPublisher publisher;

    @PostMapping
    public String createOrder(@RequestBody OrderCreatedEvent event) {
        if (event.getCreatedAt() == null) {
            event.setCreatedAt(Instant.now());
        }
        publisher.publishOrderCreated(event);
        return "Published: " + event.getOrderId();
    }
}
