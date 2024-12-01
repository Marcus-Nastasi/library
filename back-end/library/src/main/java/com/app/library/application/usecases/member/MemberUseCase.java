package com.app.library.application.usecases.member;

import com.app.library.application.gateways.member.MemberGateway;
import com.app.library.domain.entity.member.Member;
import com.app.library.domain.entity.member.MemberPaginated;

import java.time.LocalDate;
import java.util.UUID;

public class MemberUseCase {
    private final MemberGateway memberGateway;

    public MemberUseCase(MemberGateway memberGateway) {
        this.memberGateway = memberGateway;
    }

    public MemberPaginated getAll(int page, int size) {
        return memberGateway.getAll(page, size);
    }

    public Member get(UUID id) {
        return memberGateway.get(id);
    }

    public Member create(Member member) {
        member.setDateOfMembership(LocalDate.now());
        return memberGateway.create(member);
    }

    public Member update(UUID id, Member member) {
        Member toUpdate = get(id);
        return memberGateway.update(toUpdate.updateDetails(member));
    }

    public Member delete(UUID id) {
        Member member = get(id);
        memberGateway.delete(id);
        return member;
    }
}
