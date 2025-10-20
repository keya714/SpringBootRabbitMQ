package com.example.orders.messaging;

import com.example.orders.config.RabbitConfig;
import com.example.orders.model.OrderCreatedEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderCreatedListener {

    @RabbitListener(queues = RabbitConfig.Q_ORDER_CREATED)
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Received order: {}", event);
    }
}
