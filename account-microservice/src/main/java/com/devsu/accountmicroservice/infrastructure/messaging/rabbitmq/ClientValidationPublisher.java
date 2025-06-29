package com.devsu.accountmicroservice.infrastructure.messaging.rabbitmq;

import com.devsu.accountmicroservice.application.port.out.ValidateClientUseCase;
import com.devsu.library.infrastructure.messaging.rabbitmq.dto.ClientValidationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidationPublisher implements ValidateClientUseCase {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void execute(ClientValidationRequestDTO request) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_CLIENT_VALIDATION, request);
    }
}
