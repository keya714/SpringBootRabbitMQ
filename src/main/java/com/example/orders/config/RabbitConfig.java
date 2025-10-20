package com.example.orders.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "orders.exchange";
    public static final String RK_ORDER_CREATED = "order.created";
    public static final String Q_ORDER_CREATED = "orders.created.q";

    @Bean
    public TopicExchange ordersExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue orderCreatedQueue() {
        return QueueBuilder.durable(Q_ORDER_CREATED).build();
    }

    @Bean
    public Binding orderCreatedBinding(Queue orderCreatedQueue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(orderCreatedQueue).to(ordersExchange).with(RK_ORDER_CREATED);
    }
}
