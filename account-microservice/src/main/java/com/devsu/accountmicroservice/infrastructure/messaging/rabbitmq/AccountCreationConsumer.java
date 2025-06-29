package com.devsu.accountmicroservice.infrastructure.messaging.rabbitmq;

import com.devsu.accountmicroservice.application.port.in.HandleAccountCreationUseCase;
import com.devsu.accountmicroservice.application.port.out.AccountIdPostgresGenerator;
import com.devsu.accountmicroservice.domain.model.Cuenta;
import com.devsu.accountmicroservice.domain.model.enums.CuentaTipo;
import com.devsu.accountmicroservice.infrastructure.mapper.AccountMapper;
import com.devsu.accountmicroservice.infrastructure.persistence.entity.CuentaEntity;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.CuentaRepository;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.PeticionRepository;
import com.devsu.library.domain.model.enums.PeticionEstado;
import com.devsu.library.infrastructure.mapper.PeticionMapper;
import com.devsu.library.infrastructure.messaging.rabbitmq.dto.ClientValidationRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountCreationConsumer implements HandleAccountCreationUseCase {
    private final AccountIdPostgresGenerator accountIdPostgresGenerator;
    private final AccountMapper accountMapper;
    private final CuentaRepository cuentaRepository;
    private final PeticionRepository peticionRepository;
    private final PeticionMapper peticionMapper;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ACCOUNT_CREATION)
    @Override
    public void execute(ClientValidationRequestDTO request) {
        log.info("Recibida la petición de creación de cuenta para el cliente: {}", request.clientId());
        String accountId = accountIdPostgresGenerator.generateId();
        Cuenta account = new Cuenta(
                accountId,
                request.saldoInicial(),
                CuentaTipo.valueOf(request.cuentaTipo().name()), request.clientId());
        CuentaEntity accountEntity = accountMapper.toEntity(account);
        cuentaRepository.save(accountEntity);
        request.request().updateStatus(PeticionEstado.COMPLETADA, "Cuenta creada con éxito con ID: " + accountId);
        peticionRepository.save(peticionMapper.toEntity(request.request()));
        log.info("Cuenta creada con éxito: {}", accountId);
    }
}
