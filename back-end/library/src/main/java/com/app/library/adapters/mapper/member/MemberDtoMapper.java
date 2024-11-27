package com.app.library.adapters.mapper.member;

import com.app.library.adapters.input.member.MemberRequestDto;
import com.app.library.adapters.output.member.MemberResponseDto;
import com.app.library.domain.entity.member.Member;

public class MemberDtoMapper {
    public MemberResponseDto mapToResponse(Member member) {
        return new MemberResponseDto(
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

    public Member mapFromRequest(MemberRequestDto member) {
        return new Member(
                member.id(),
                member.name(),
                member.cpf(),
                member.type(),
                member.dateOfMembership(),
                member.booksIssued(),
                member.booksLimit(),
                member.phone()
        );
    }
}
