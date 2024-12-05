package com.app.library.application.usecases.book;

import com.app.library.application.gateways.aws.FileManagerGateway;
import com.app.library.application.gateways.book.BookGateway;
import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;

import java.util.UUID;

public class BookUseCase {
    private final BookGateway bookGateway;
    private final FileManagerGateway fileManager;

    public BookUseCase(BookGateway bookGateway, FileManagerGateway fileManager) {
        this.bookGateway = bookGateway;
        this.fileManager = fileManager;
    }

    public BookPaginated getAll(int page, int size) {
        return bookGateway.getAll(page, size);
    }

    public Book get(UUID id) {
        return bookGateway.get(id);
    }

    public Book create(Book book, byte[] fileData, String fileName) {
        book.setImage_url(null);
        Book created = bookGateway.create(book);
        if (fileData != null && fileName != null) {
            String fileUrl = fileManager.upload(fileData, fileName);
            created.setImage_url(fileUrl);
            return bookGateway.update(created);
        }
        return created;
    }

    public Book update(UUID id, Book book) {
        Book toUpdate = get(id);
        return bookGateway.update(toUpdate.updateDetails(book));
    }

    public Book delete(UUID id) {
        Book book = get(id);
        if (book.getImage_url() != null) fileManager.delete(book.getImage_url());
        return bookGateway.delete(id);
    }
}
