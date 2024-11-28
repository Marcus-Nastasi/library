package com.app.library.domain.entity.rent;

import com.app.library.domain.entity.member.Member;

import java.time.LocalDate;
import java.util.UUID;

public class Rent {
    private UUID id;
    private UUID book_id;
    private LocalDate emit_date;
    private LocalDate return_date;
    private UUID librarian_id;
    private UUID member_id;
    private Member member;

    public Rent updateDetails(Rent updatedRent) {
        if (updatedRent.book_id == null) throw new IllegalArgumentException("Book cannot be null");
        if (updatedRent.member_id == null) throw new IllegalArgumentException("Member cannot be null");
        if (updatedRent.librarian_id == null) throw new IllegalArgumentException("Librarian cannot be null");
        this.setId(updatedRent.getId());
        this.setBook_id(updatedRent.getBook_id());
        this.setEmit_date(updatedRent.getEmit_date());
        this.setReturn_date(updatedRent.getReturn_date());
        this.setLibrarian_id(updatedRent.getLibrarian_id());
        this.setMember_id(updatedRent.getMember_id());
        this.setMember(updatedRent.getMember());
        return updatedRent;
    }

    public Rent() {}

    public Rent(UUID id, UUID book_id, LocalDate emit_date, LocalDate return_date, UUID librarian_id, UUID member_id) {
        this.id = id;
        this.book_id = book_id;
        this.emit_date = emit_date;
        this.return_date = return_date;
        this.librarian_id = librarian_id;
        this.member_id = member_id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBook_id() {
        return book_id;
    }

    public void setBook_id(UUID book_id) {
        this.book_id = book_id;
    }

    public LocalDate getEmit_date() {
        return emit_date;
    }

    public void setEmit_date(LocalDate emit_date) {
        this.emit_date = emit_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public UUID getLibrarian_id() {
        return librarian_id;
    }

    public void setLibrarian_id(UUID librarian_id) {
        this.librarian_id = librarian_id;
    }

    public UUID getMember_id() {
        return member_id;
    }

    public void setMember_id(UUID member_id) {
        this.member_id = member_id;
    }
}
