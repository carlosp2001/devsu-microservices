package com.devsu.usermicroservice.infrastructure.persistence.repository;

import com.devsu.usermicroservice.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClienteEntity, String> {
}
