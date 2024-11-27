package com.app.library.application.usecases.member;

import com.app.library.application.gateways.member.MemberGateway;
import com.app.library.domain.entity.member.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MemberUseCase {
    private final MemberGateway memberGateway;

    public MemberUseCase(MemberGateway memberGateway) {
        this.memberGateway = memberGateway;
    }

    public List<Member> getAll() {
        return memberGateway.getAll();
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
