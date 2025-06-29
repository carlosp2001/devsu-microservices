package com.devsu.accountmicroservice.infrastructure.messaging.rabbitmq;

import com.devsu.accountmicroservice.application.port.in.HandleAccountValidationFailedUseCase;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.PeticionRepository;
import com.devsu.library.domain.model.Peticion;
import com.devsu.library.domain.model.enums.PeticionEstado;
import com.devsu.library.infrastructure.mapper.PeticionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountValidationFailedConsumer implements HandleAccountValidationFailedUseCase {
    private final PeticionRepository peticionRepository;
    private final PeticionMapper peticionMapper;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENT_VALIDATION_FAILED)
    @Override
    public void execute(Peticion request) {
        log.info("Recibida la petición de validación fallida de la cuenta: {}", request.getPeticionId());
        request.updateStatus(PeticionEstado.FALLIDA, "Validación de cuenta fallida");
        peticionRepository.save(peticionMapper.toEntity(request));
        log.warn("Validación de cuenta fallida para la petición: {}", request.getPeticionId());
    }
}
