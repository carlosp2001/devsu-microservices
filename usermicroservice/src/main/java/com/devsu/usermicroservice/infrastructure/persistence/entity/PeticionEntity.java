package com.devsu.usermicroservice.infrastructure.persistence.entity;

import com.devsu.usermicroservice.infrastructure.persistence.enums.PeticionEstado;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "peticion")
public class PeticionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID peticionId;

    @Enumerated(EnumType.STRING)
    private PeticionEstado estado;

    private String mensaje;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private LocalDateTime updatedAt;
}
