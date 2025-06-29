package com.devsu.usermicroservice.infrastructure.messaging.rabbitmq;

import com.devsu.usermicroservice.application.port.in.ClientValidationUseCase;
import com.devsu.usermicroservice.infrastructure.persistence.repository.ClientRepository;
import com.devsu.library.infrastructure.messaging.rabbitmq.dto.ClientValidationRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientValidationConsumer implements ClientValidationUseCase {
    private final ClientRepository clientRepository;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENT_VALIDATION)
    @Override
    public void execute(ClientValidationRequestDTO request) {
        log.info("Recibida la petición de validación del cliente: {}", request.clientId());
        if (clientRepository.existsById(request.clientId())) {
            log.info("Cliente con ID {} existe. Procediendo a validar.", request.clientId());
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    RabbitMQConfig.ROUTING_KEY_ACCOUNT_CREATION,
                    request
            );
        } else {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    RabbitMQConfig.ROUTING_KEY_CLIENT_VALIDATION_FAILED,
                    request.request()
            );
            log.warn("Cliente con ID {} no existe. No se puede validar.", request.clientId());
        }
    }
}
