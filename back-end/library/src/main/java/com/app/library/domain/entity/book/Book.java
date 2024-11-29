package com.app.library.domain.entity.book;

import java.time.LocalDate;
import java.util.UUID;

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

    public Book(UUID id) {}

    public Book(UUID id, String author, String name, double price, int quantity, String image_url, boolean isAvailable, BookType type, String edition, LocalDate dateOfPublish) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image_url = image_url;
        this.isAvailable = isAvailable;
        this.type = type;
        this.edition = edition;
        this.dateOfPublish = dateOfPublish;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public LocalDate getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(LocalDate dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }
}
