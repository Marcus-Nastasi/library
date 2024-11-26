package com.app.library.infrastructure.entities.book;

import com.app.library.domain.entities.book.Book;
import com.app.library.domain.entities.book.BookType;
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

    public Book mapToBook() {
        return new Book(
            this.id,
            this.author,
            this.name,
            this.price,
            this.quantity,
            this.image_url,
            this.isAvailable,
            this.type,
            this.edition,
            this.dateOfPublish
        );
    }
}
