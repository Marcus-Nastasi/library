package com.app.library.application.gateways.member;

import com.app.library.domain.entity.member.Member;

import java.util.List;
import java.util.UUID;

public interface MemberGateway {
    List<Member> getAll();
    Member get(UUID id);
    Member create(Member member);
    Member update(Member member);
    Member delete(UUID id);
}
