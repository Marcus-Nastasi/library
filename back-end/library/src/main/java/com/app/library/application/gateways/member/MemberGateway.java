package com.app.library.application.gateways.member;

import com.app.library.domain.entity.member.Member;
import com.app.library.domain.entity.member.MemberPaginated;

import java.util.UUID;

public interface MemberGateway {
    MemberPaginated getAll(int page, int size);
    Member get(UUID id);
    Member create(Member member);
    Member update(Member member);
    Member delete(UUID id);
    Member getByCpf(String cpf);
}
