package com.app.library.infrastructure.mapper.member;

import com.app.library.domain.entity.member.Member;
import com.app.library.domain.entity.member.MemberPaginated;
import com.app.library.infrastructure.entity.member.MemberEntity;
import org.springframework.data.domain.Page;

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

    public MemberPaginated mapToMemberPaginated(Page<MemberEntity> memberEntities) {
        return new MemberPaginated(
            memberEntities.getNumber(),
            memberEntities.getSize(),
            memberEntities.getTotalPages(),
            memberEntities.map(this::mapFromMemberEntity).toList()
        );
    }
}
