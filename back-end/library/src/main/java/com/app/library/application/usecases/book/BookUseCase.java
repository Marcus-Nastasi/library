package com.app.library.application.usecases.book;

import com.app.library.application.gateways.book.BookGateway;
import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;
import com.app.library.infrastructure.gateway.aws.FileManager;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class BookUseCase {
    private final BookGateway bookGateway;
    private final FileManager fileManager;

    public BookUseCase(BookGateway bookGateway, FileManager fileManager) {
        this.bookGateway = bookGateway;
        this.fileManager = fileManager;
    }

    public BookPaginated getAll(int page, int size) {
        return bookGateway.getAll(page, size);
    }

    public Book get(UUID id) {
        return bookGateway.get(id);
    }

    public Book create(Book book, MultipartFile file) {
        book.setImage_url(null);
        Book created = bookGateway.create(book);
        if (file != null) {
            String file_url = fileManager.uploadImage(file);
            created.setImage_url(file_url);
            return bookGateway.create(created);
        }
        return created;
    }

    public Book update(UUID id, Book book) {
        Book toUpdate = get(id);
        return bookGateway.update(toUpdate.updateDetails(book));
    }

    public Book delete(UUID id) {
        Book book = get(id);
        try {
            fileManager.deleteImage(book.getImage_url());
            return bookGateway.delete(id);
        } catch (Exception e) {
            return bookGateway.delete(id);
        }
    }
}
