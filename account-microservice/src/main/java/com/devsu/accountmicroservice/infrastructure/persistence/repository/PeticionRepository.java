package com.devsu.accountmicroservice.infrastructure.persistence.repository;

import com.devsu.library.infrastructure.persistence.entity.PeticionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PeticionRepository extends JpaRepository<PeticionEntity, UUID> {

}
