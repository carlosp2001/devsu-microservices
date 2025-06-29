package com.devsu.usermicroservice.infrastructure.messaging.rabbitmq;

import com.devsu.usermicroservice.application.port.out.AccountCreationUseCase;
import com.devsu.library.infrastructure.messaging.rabbitmq.dto.ClientValidationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountCreationPublisher implements AccountCreationUseCase {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void execute(ClientValidationRequestDTO request) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_ACCOUNT_CREATION, request);
    }
}
