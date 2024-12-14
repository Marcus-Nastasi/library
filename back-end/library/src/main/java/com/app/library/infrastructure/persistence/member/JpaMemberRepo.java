package com.app.library.infrastructure.persistence.member;

import com.app.library.infrastructure.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaMemberRepo extends JpaRepository<MemberEntity, UUID> {

    MemberEntity findByCpf(String cpf);

    List<MemberEntity> findByNameContaining(String name);
}
