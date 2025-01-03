package com.app.library.infrastructure.persistence.rent;

import com.app.library.infrastructure.entity.rent.RentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaRentRepo extends JpaRepository<RentEntity, UUID> {

    Page<RentEntity> findByMemberId(UUID member_id, Pageable pageable);

    List<RentEntity> findByBookId(UUID book_id);
}
