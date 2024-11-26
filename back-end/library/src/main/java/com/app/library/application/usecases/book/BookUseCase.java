package com.app.library.application.usecases.book;

import com.app.library.application.gateways.book.BookGateway;
import com.app.library.domain.entity.book.Book;
import com.app.library.infrastructure.gateway.aws.FileManager;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public class BookUseCase {
    private final BookGateway bookGateway;
    private final FileManager fileManager;

    public BookUseCase(BookGateway bookGateway, FileManager fileManager) {
        this.bookGateway = bookGateway;
        this.fileManager = fileManager;
    }

    public List<Book> getAll() {
        return bookGateway.getAll();
    }

    public Book get(UUID id) {
        return bookGateway.get(id);
    }

    public Book create(Book book, MultipartFile file) {
        String file_url = null;
        if (file != null) file_url = fileManager.uploadImage(file);
        book.setImage_url(file_url);
        return bookGateway.create(book);
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
