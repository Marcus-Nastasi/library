package com.app.library.infrastructure.entity.member;

import com.app.library.domain.entity.member.MemberType;
import com.app.library.domain.entity.rent.Rent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Table(name = "members")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String cpf;
    @Enumerated(value = EnumType.ORDINAL)
    private MemberType type;
    @Column(name = "date_of_membership")
    private LocalDate dateOfMembership;
    @Column(name = "books_issued")
    private int booksIssued;
    @Column(name = "books_limit")
    private int booksLimit;
    private String phone;
}
