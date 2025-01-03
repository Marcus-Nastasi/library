package com.app.library.application.usecases.book;

import com.app.library.application.exception.ApplicationException;
import com.app.library.application.gateways.aws.FileManagerGateway;
import com.app.library.application.gateways.book.BookGateway;
import com.app.library.application.gateways.rent.RentGateway;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;
import com.app.library.domain.entity.book.BookType;
import com.app.library.domain.entity.rent.Rent;

import java.time.LocalDate;
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
        Book book = bookGateway.get(id);
        if (book == null) throw new ApplicationException("book not found");
        return book;
    }

    public BookPaginated getByName(String name, int page, int size) {
        BookPaginated book = bookGateway.getByName(name, page, size);
        if (book == null) throw new ApplicationException("book not found");
        return book;
    }

    public BookPaginated getByAuthor(String author, int page, int size) {
        BookPaginated book = bookGateway.getByAuthor(author, page, size);
        if (book == null) throw new ApplicationException("book not found");
        return book;
    }

    public BookPaginated getByType(BookType bookType, int page, int size) {
        BookPaginated book = bookGateway.getByType(bookType, page, size);
        if (book == null) throw new ApplicationException("books not found");
        return book;
    }

    public BookPaginated getByDateOfPublish(LocalDate localDate, int page, int size) {
        BookPaginated book = bookGateway.getByDateOfPublish(localDate, page, size);
        if (book == null) throw new ApplicationException("books not found");
        return book;
    }

    public Book create(Book book, byte[] fileData, String fileName) {
        book.setImage_url(null);
        Book created = bookGateway.save(book);
        if (fileData != null && fileName != null) {
            String fileUrl = fileManagerGateway.upload(fileData, fileName);
            created.setImage_url(fileUrl);
            return bookGateway.save(created);
        }
        return created;
    }

    public Book update(UUID id, Book book) {
        Book toUpdate = get(id);
        if (toUpdate == null) throw new ApplicationException("book not found");
        return bookGateway.save(toUpdate.updateDetails(book));
    }

    public Book delete(UUID id) {
        Book book = get(id);
        List<Rent> rent = rentGateway.getByBookId(id);
        if (rent != null && !rent.isEmpty()) {
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
