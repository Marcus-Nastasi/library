package com.app.library.domain.entity.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private UUID id;
    private String name;
    private String cpf;
    private MemberType type;
    private LocalDate dateOfMembership;
    private int booksIssued;
    private int booksLimit;
    private String phone;

    public Member updateDetails(Member updatedMember) {
        if (updatedMember.cpf == null) throw new IllegalArgumentException("Cpf cannot be null");
        if (updatedMember.name == null) throw new IllegalArgumentException("Name cannot be null");
        this.setId(updatedMember.getId());
        this.setName(updatedMember.getName());
        this.setCpf(updatedMember.getCpf());
        this.setType(updatedMember.getType());
        this.setDateOfMembership(updatedMember.getDateOfMembership());
        this.setBooksIssued(updatedMember.getBooksIssued());
        this.setBooksLimit(updatedMember.getBooksLimit());
        this.setPhone(updatedMember.getPhone());
        return updatedMember;
    }
}
