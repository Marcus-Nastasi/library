package com.app.library.infrastructure.persistence.member;

import com.app.library.infrastructure.entity.member.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMemberRepo extends JpaRepository<MemberEntity, UUID> {

    MemberEntity findByCpf(String cpf);

    Page<MemberEntity> findByNameContaining(String name, Pageable pageable);
}
