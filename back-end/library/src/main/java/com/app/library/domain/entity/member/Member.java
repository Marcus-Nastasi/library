package com.app.library.domain.entity.member;

import com.app.library.domain.entity.rent.Rent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
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
    //private List<Rent> rents;

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
        //this.setRents(updatedMember.getRents());
        return updatedMember;
    }
}
