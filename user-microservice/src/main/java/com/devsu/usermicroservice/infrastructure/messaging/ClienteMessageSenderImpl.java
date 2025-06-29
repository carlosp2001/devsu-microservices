package com.devsu.usermicroservice.infrastructure.messaging;

import com.devsu.usermicroservice.application.port.out.ClienteMessageSender;
import com.devsu.usermicroservice.domain.model.Cliente;
import com.devsu.usermicroservice.domain.model.Peticion;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteMessageSenderImpl implements ClienteMessageSender {

    private final AmqpTemplate amqpTemplate;

    @Override
    public void sendUserCreationMessage(Cliente client, Peticion peticion) {

    }
}
