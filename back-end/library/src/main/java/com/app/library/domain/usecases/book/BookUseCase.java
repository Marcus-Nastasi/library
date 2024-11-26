package com.app.library.domain.usecases.book;

import com.app.library.adapters.output.book.JpaBookRepo;
import com.app.library.domain.entities.book.Book;
import com.app.library.domain.entities.book.BookType;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
public class BookUseCase {
    private final JpaBookRepo repo;

    public Book create(
            String author,
            String name,
            double price,
            int quantity,
            String image_url,
            boolean isAvailable,
            BookType type,
            String edition,
            LocalDate dateOfPublish
    ) {
        Book book = new Book();
        book.setAuthor(author);
        book.setName(name);
        book.setPrice(price);
        book.setQuantity(quantity);
        book.setImage_url(image_url);
        book.setAvailable(isAvailable);
        book.setType(type);
        book.setEdition(edition);
        book.setDateOfPublish(dateOfPublish);
        repo.save(book.mapToBookEntity());
        return book;
    }

    public Book deleteById(UUID id) {
        return repo.findById(id).orElseThrow(RuntimeException::new).mapToBook();
    }
}
