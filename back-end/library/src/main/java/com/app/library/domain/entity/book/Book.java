package com.app.library.domain.entity.book;

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

    public Book updateDetails(Book updatedBook) {
        if (updatedBook.price < 0) throw new IllegalArgumentException("Price cannot be negative");
        if (updatedBook.quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        this.setId(updatedBook.getId());
        this.setName(updatedBook.getName());
        this.setAuthor(updatedBook.getAuthor());
        this.setAvailable(updatedBook.isAvailable());
        this.setPrice(updatedBook.getPrice());
        this.setQuantity(updatedBook.getQuantity());
        this.setImage_url(updatedBook.getImage_url());
        this.setType(updatedBook.getType());
        this.setEdition(updatedBook.getEdition());
        this.setDateOfPublish(updatedBook.getDateOfPublish());
        return updatedBook;
    }
}
