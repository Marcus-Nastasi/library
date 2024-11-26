package com.app.library.domain.entity.book;

import com.app.library.infrastructure.entities.book.BookEntity;
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
public class Book {
    private UUID id;
    private String author;
    private String name;
    private double price;
    private int quantity;
    private String image_url;
    private boolean isAvailable;
    private BookType type;
    private String edition;
    private LocalDate dateOfPublish;
}
