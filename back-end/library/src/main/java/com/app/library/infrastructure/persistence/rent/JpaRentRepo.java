package com.app.library.infrastructure.persistence.rent;

import com.app.library.infrastructure.entity.rent.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRentRepo extends JpaRepository<RentEntity, UUID> {}
