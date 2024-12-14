package com.app.library.application.usecases.member;

import com.app.library.application.exception.ApplicationException;
import com.app.library.application.gateways.member.MemberGateway;
import com.app.library.domain.entity.member.Member;
import com.app.library.domain.entity.member.MemberPaginated;

import java.time.LocalDate;
import java.util.List;
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

    public Member getByCpf(String cpf) {
        return memberGateway.getByCpf(cpf);
    }

    public List<Member> getByName(String name) {
        return memberGateway.getByName(name);
    }

    public Member create(Member member) {
        member.setDateOfMembership(LocalDate.now());
        return memberGateway.create(member);
    }

    public Member update(UUID id, Member member) {
        Member toUpdate = get(id);
        if (toUpdate == null) throw new ApplicationException("member not found");
        return memberGateway.update(toUpdate.updateDetails(member));
    }

    public Member delete(UUID id) {
        Member toDelete = get(id);
        if (toDelete == null) throw new ApplicationException("member not found");
        return memberGateway.delete(id);
    }

    public void increaseIssueBook(UUID id) {
        Member member = get(id);
        if (member == null) throw new ApplicationException("member not found");
        member.increaseIssueBook();
        update(id, member);
    }

    public void decreaseIssueBook(UUID id) {
        Member member = get(id);
        if (member == null) throw new ApplicationException("member not found");
        member.decreaseIssueBook();
        update(id, member);
    }
}
