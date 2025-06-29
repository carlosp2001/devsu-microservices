package com.devsu.usermicroservice.application.port.out;

import com.devsu.usermicroservice.domain.model.Cliente;
import com.devsu.usermicroservice.domain.model.Peticion;

public interface ClienteMessageSender {
    void sendUserCreationMessage(Cliente client, Peticion peticion);
}
