package com.app.library.adapters.mapper.rent;

import com.app.library.adapters.input.rent.RentRequestDto;
import com.app.library.adapters.output.rent.RentPaginatedResponseDto;
import com.app.library.adapters.output.rent.RentResponseDto;
import com.app.library.application.usecases.book.BookUseCase;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.domain.entity.rent.Rent;
import com.app.library.domain.entity.rent.RentPaginated;
import org.springframework.beans.factory.annotation.Autowired;

public class RentDtoMapper {

    @Autowired
    private MemberUseCase memberUseCase;
    @Autowired
    private BookUseCase bookUseCase;

    public RentResponseDto mapToResponse(Rent rent) {
        return new RentResponseDto(
            rent.getId(),
            rent.getEmit_date(),
            rent.getReturn_date(),
            rent.isReturned(),
            rent.getLibrarian_id(),
            bookUseCase.get(rent.getBook_id()),
            memberUseCase.get(rent.getMember_id())
        );
    }

    public Rent mapFromRequest(RentRequestDto rent) {
        return new Rent(
            rent.id(),
            rent.book_id(),
            rent.emit_date(),
            rent.return_date(),
            rent.librarian_id(),
            rent.member_id(),
            rent.returned()
        );
    }

    public RentPaginatedResponseDto mapToRentPaginatedResponse(RentPaginated rentPaginated) {
        return new RentPaginatedResponseDto(
            rentPaginated.getPage(),
            rentPaginated.getSize(),
            rentPaginated.getTotal(),
            rentPaginated.getData().stream().map(this::mapToResponse).toList()
        );
    }
}
