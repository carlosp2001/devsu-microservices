package com.devsu.usermicroservice.infrastructure.messaging.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "user_exchange";
    public static final String QUEUE_CLIENT_VALIDATION = "client-validation-queue";
    public static final String ROUTING_KEY_CLIENT_VALIDATION = "client.validation";

    public static final String QUEUE_ACCOUNT_CREATION = "account-creation-queue";
    public static final String ROUTING_KEY_ACCOUNT_CREATION = "account.creation";

    public static final String QUEUE_CLIENT_VALIDATION_FAILED = "client-validation-failed-queue";
    public static final String ROUTING_KEY_CLIENT_VALIDATION_FAILED = "client.validation.failed";


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue clientValidationQueue() {
        return new Queue(QUEUE_CLIENT_VALIDATION);
    }

    @Bean
    public Queue clientValidationFailedQueue() {
        return new Queue(QUEUE_CLIENT_VALIDATION_FAILED);
    }

    @Bean
    public Queue accountCreationQueue() {
        return new Queue(QUEUE_ACCOUNT_CREATION);
    }

    @Bean
    public Binding userValidationFailedBinding(@Qualifier("clientValidationFailedQueue") Queue clientValidationFailedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(clientValidationFailedQueue).to(exchange).with(ROUTING_KEY_CLIENT_VALIDATION_FAILED);
    }

    @Bean
    public Binding userValidationBinding(@Qualifier("clientValidationQueue") Queue clientValidationQueue, TopicExchange exchange) {
        return BindingBuilder.bind(clientValidationQueue).to(exchange).with(ROUTING_KEY_CLIENT_VALIDATION);
    }

    @Bean
    public Binding accountCreationBinding(@Qualifier("accountCreationQueue") Queue accountCreationQueue, TopicExchange exchange) {
        return BindingBuilder.bind(accountCreationQueue).to(exchange).with(ROUTING_KEY_ACCOUNT_CREATION);
    }
}
