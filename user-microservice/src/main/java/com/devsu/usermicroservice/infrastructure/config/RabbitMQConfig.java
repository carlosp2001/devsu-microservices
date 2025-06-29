package com.devsu.usermicroservice.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "user_queue";
    public static final String EXCHANGE_NAME = "user_exchange";
    public static final String ROUTING_KEY_USER_CREATION = "user.creation";
    public static final String ROUTING_KEY_USER_UPDATE = "user.update";
    public static final String ROUTING_KEY_USER_DELETION = "user.deletion";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingUserCreation(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_USER_CREATION);
    }

    @Bean
    public Binding bindingUserUpdate(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_USER_UPDATE);
    }

    @Bean
    public Binding bindingUserDeletion(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_USER_DELETION);
    }
}
