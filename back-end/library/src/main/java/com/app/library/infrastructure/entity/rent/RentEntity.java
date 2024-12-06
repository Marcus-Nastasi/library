package com.app.library.infrastructure.entity.rent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "rents")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "book_id", nullable = false)
    private UUID bookId;
    private LocalDate emit_date;
    private LocalDate return_date;
    private boolean returned;
    @Column(name = "librarian_id", nullable = false)
    private UUID librarianId;
    @Column(name = "member_id", nullable = false)
    private UUID memberId;
}
