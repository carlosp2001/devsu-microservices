package com.devsu.usermicroservice.domain.model.integration;

import com.devsu.usermicroservice.application.command.CreateClientCommand;
import com.devsu.usermicroservice.application.port.in.CreateClientUseCase;
import com.devsu.usermicroservice.infrastructure.persistence.entity.ClienteEntity;
import com.devsu.usermicroservice.infrastructure.persistence.repository.ClientRepository;
import com.devsu.usermicroservice.infrastructure.rest.dto.CreateClientResponseDTO;
import com.devsu.usermicroservice.infrastructure.rest.dto.enums.PersonaGeneroDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ClienteIntegrationTest {

    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpassword");

    private static final RabbitMQContainer rabbitmqContainer = new RabbitMQContainer("rabbitmq:3.12-management")
            .withUser("guest", "guest")
            .withVhost("/")
            .withExposedPorts(5672, 15672);

    @Autowired
    private CreateClientUseCase createClientUseCase;

    @Autowired
    private ClientRepository clientRepository;

    static {
        postgresContainer.start();
        rabbitmqContainer.start();
    }

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {

        // PostgreSQL
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);

        // RabbitMQ
        registry.add("spring.rabbitmq.host", rabbitmqContainer::getHost);
        registry.add("spring.rabbitmq.port", () -> rabbitmqContainer.getMappedPort(5672).toString());
        registry.add("spring.rabbitmq.username", () -> "guest");
        registry.add("spring.rabbitmq.password", () -> "guest");

        registry.add("server.port", () -> "8080");

    }

    @AfterAll
    static void stopContainer() {
        postgresContainer.stop();
        rabbitmqContainer.stop();
    }

    @Test
    public void testCreateClient() {
        CreateClientCommand command = new CreateClientCommand(
                "John Doe",
                PersonaGeneroDTO.MASCULINO,
                (short) 30,
                "1234567890",
                "123 Main St",
                "password"
        );

        CreateClientResponseDTO response = createClientUseCase.execute(command);

        assertThat(response).isNotNull();
        assertThat(response.id()).isNotNull();
        assertThat(response.nombre()).isEqualTo(command.nombre());
        assertThat(response.edad()).isEqualTo(command.edad());
        assertThat(response.genero()).isEqualTo(command.genero());
        assertThat(response.direccion()).isEqualTo(command.direccion());
        assertThat(response.telefono()).isEqualTo(command.telefono());
        assertThat(response.estado()).isTrue();
        assertThat(response.password()).isEqualTo(command.password());
        assertThat(response.createdAt()).isNotNull();
        assertThat(response.updatedAt()).isNotNull();

        ClienteEntity clienteEntity = clientRepository.findById(response.id()).orElse(null);
        assertThat(clienteEntity).isNotNull();
        assertThat(clienteEntity.getPersonaEntity().getNombre()).isEqualTo(command.nombre());
        assertThat(clienteEntity.getPersonaEntity().getEdad()).isEqualTo(command.edad());
        assertThat(clienteEntity.getPersonaEntity().getGenero()).isEqualTo(command.genero().name());
        assertThat(clienteEntity.getPersonaEntity().getDireccion()).isEqualTo(command.direccion());
        assertThat(clienteEntity.getPersonaEntity().getTelefono()).isEqualTo(command.telefono());
        assertThat(clienteEntity.getEstado()).isTrue();
        assertThat(clienteEntity.getPassword()).isEqualTo(command.password());
    }
}
