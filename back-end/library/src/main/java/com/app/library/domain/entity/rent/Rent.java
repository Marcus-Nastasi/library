package com.app.library.domain.entity.rent;

import com.app.library.domain.entity.member.Member;
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
public class Rent {
    private UUID id;
    private UUID book_id;
    private LocalDate emit_date;
    private LocalDate return_date;
    private UUID librarian_id;
    private UUID member_id;
    //private Member member;

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
        //this.setMember(updatedRent.getMember());
        return updatedRent;
    }
}
