package com.app.library.infrastructure.mapper.member;

import com.app.library.domain.entity.member.Member;
import com.app.library.infrastructure.entity.member.MemberEntity;

public class MemberEntityMapper {
    public MemberEntity mapToMemberEntity(Member member) {
        return new MemberEntity(
            member.getId(),
            member.getName(),
            member.getCpf(),
            member.getType(),
            member.getDateOfMembership(),
            member.getBooksIssued(),
            member.getBooksLimit(),
            member.getPhone()
        );
    }

    public Member mapFromMemberEntity(MemberEntity member) {
        return new Member(
            member.getId(),
            member.getName(),
            member.getCpf(),
            member.getType(),
            member.getDateOfMembership(),
            member.getBooksIssued(),
            member.getBooksLimit(),
            member.getPhone()
        );
    }
}
