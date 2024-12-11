package com.app.library.application.usecases.book;

import com.app.library.application.exception.ApplicationException;
import com.app.library.application.gateways.aws.FileManagerGateway;
import com.app.library.application.gateways.book.BookGateway;
import com.app.library.application.gateways.rent.RentGateway;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;
import com.app.library.domain.entity.rent.Rent;

import java.util.List;
import java.util.UUID;

public class BookUseCase {
    private final BookGateway bookGateway;
    private final FileManagerGateway fileManagerGateway;
    private final RentGateway rentGateway;
    private final MemberUseCase memberUseCase;

    public BookUseCase(BookGateway bookGateway, FileManagerGateway fileManagerGateway, RentGateway rentGateway, MemberUseCase memberUseCase) {
        this.bookGateway = bookGateway;
        this.fileManagerGateway = fileManagerGateway;
        this.rentGateway = rentGateway;
        this.memberUseCase = memberUseCase;
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
            String fileUrl = fileManagerGateway.upload(fileData, fileName);
            created.setImage_url(fileUrl);
            return bookGateway.update(created);
        }
        return created;
    }

    public Book update(UUID id, Book book) {
        Book toUpdate = get(id);
        if (toUpdate == null) throw new ApplicationException("book not found");
        return bookGateway.update(toUpdate.updateDetails(book));
    }

    public Book delete(UUID id) {
        Book book = get(id);
        List<Rent> rent = rentGateway.getByBookId(id);
        if (rent != null) {
            rent.forEach(r -> {
                rentGateway.delete(r.getId());
                memberUseCase.decreaseIssueBook(r.getMember_id());
            });
        }
        if (book.getImage_url() != null) fileManagerGateway.delete(book.getImage_url());
        return bookGateway.delete(id);
    }

    public void decreaseQuantity(UUID id) {
        Book book = get(id);
        if (book == null) throw new ApplicationException("book not found");
        if (!book.isAvailable()) throw new ApplicationException("book unavailable");
        book.decreaseQuantity();
        if (book.getQuantity() == 0) book.setAvailable(false);
        update(id, book);
    }

    public void increaseQuantity(UUID id) {
        Book book = get(id);
        if (book == null) throw new ApplicationException("book not found");
        book.increaseQuantity();
        if (!book.isAvailable()) book.setAvailable(true);
        update(id, book);
    }
}
