package com.app.library.infrastructure.entities.book;

import com.app.library.domain.entity.book.BookType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "books")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String author;
    @Column
    private String name;
    @Column
    private double price;
    @Column
    private int quantity;
    @Column
    private String image_url;
    @Column
    private boolean isAvailable;
    @Column
    private BookType type;
    @Column
    private String edition;
    @Column
    private LocalDate dateOfPublish;
}
