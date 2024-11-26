package com.app.library.adapters.mapper.book;

import com.app.library.adapters.input.book.BookRequestDto;
import com.app.library.adapters.output.book.BookResponseDto;
import com.app.library.domain.entity.book.Book;

public class BookDtoMapper {
    public BookResponseDto mapToResponse(Book book) {
        return new BookResponseDto(
            book.getId(),
            book.getAuthor(),
            book.getName(),
            book.getPrice(),
            book.getQuantity(),
            book.getImage_url(),
            book.isAvailable(),
            book.getType(),
            book.getEdition(),
            book.getDateOfPublish()
        );
    }

    public Book mapFromRequest(BookRequestDto bookRequestDto) {
        return new Book(
            null,
            bookRequestDto.author(),
            bookRequestDto.name(),
            bookRequestDto.price(),
            bookRequestDto.quantity(),
            bookRequestDto.image_url(),
            bookRequestDto.isAvailable(),
            bookRequestDto.type(),
            bookRequestDto.edition(),
            bookRequestDto.dateOfPublish()
        );
    }
}
