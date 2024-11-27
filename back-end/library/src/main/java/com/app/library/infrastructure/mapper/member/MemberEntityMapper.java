package com.app.library.infrastructure.mapper.member;

import com.app.library.domain.entity.member.Member;
import com.app.library.infrastructure.entity.member.MemberEntity;

public class MemberEntityMapper {
    public MemberEntity mapToLibrarianEntity(Member member) {
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

    public Member mapFromLibrarianEntity(MemberEntity member) {
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
