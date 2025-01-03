package com.app.library.domain.entity.rent;

import com.app.library.domain.entity.exception.DomainException;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Rent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID book_id;
    private LocalDate emit_date;
    private LocalDate return_date;
    private boolean returned;
    private UUID librarian_id;
    private UUID member_id;

    public Rent updateDetails(Rent updatedRent) {
        if (updatedRent.book_id == null) throw new DomainException("Book cannot be null");
        if (updatedRent.member_id == null) throw new DomainException("Member cannot be null");
        if (updatedRent.librarian_id == null) throw new DomainException("Librarian cannot be null");
        this.setId(updatedRent.getId());
        this.setBook_id(updatedRent.getBook_id());
        this.setEmit_date(updatedRent.getEmit_date());
        this.setReturn_date(updatedRent.getReturn_date());
        this.setReturned(updatedRent.isReturned());
        this.setLibrarian_id(updatedRent.getLibrarian_id());
        this.setMember_id(updatedRent.getMember_id());
        return updatedRent;
    }

    public Rent() {}

    public Rent(UUID id, UUID book_id, LocalDate emit_date, LocalDate return_date, UUID librarian_id, UUID member_id, boolean returned) {
        this.id = id;
        this.book_id = book_id;
        this.emit_date = emit_date;
        this.return_date = return_date;
        this.librarian_id = librarian_id;
        this.member_id = member_id;
        this.returned = returned;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
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
