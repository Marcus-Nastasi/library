package com.app.library.domain.entity.member;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Member implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private String cpf;
    private MemberType type;
    private LocalDate dateOfMembership;
    private int booksIssued;
    private int booksLimit;
    private String phone;

    public Member() {}

    public Member(UUID id, String name, String cpf, MemberType type, LocalDate dateOfMembership, int booksIssued, int booksLimit, String phone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.booksIssued = booksIssued;
        this.booksLimit = booksLimit;
        this.phone = phone;
    }

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(LocalDate dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public int getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(int booksIssued) {
        this.booksIssued = booksIssued;
    }

    public int getBooksLimit() {
        return booksLimit;
    }

    public void setBooksLimit(int booksLimit) {
        this.booksLimit = booksLimit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
