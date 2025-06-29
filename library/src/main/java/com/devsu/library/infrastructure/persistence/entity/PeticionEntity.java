package com.devsu.library.infrastructure.persistence.entity;

import com.devsu.library.domain.model.enums.PeticionEstado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "peticion")
@Setter
@Getter
public class PeticionEntity {
    @Id
    private UUID peticionId;

    @Enumerated(EnumType.STRING)
    private PeticionEstado estado;

    private String mensaje;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private LocalDateTime updatedAt;
}
