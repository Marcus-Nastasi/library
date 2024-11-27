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
    private UUID book_id;
    private LocalDate emit_date;
    private LocalDate return_date;
    private UUID librarian_id;
    private UUID member_id;
    /*
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;*/
}
