package com.app.library.adapters.mapper.rent;

import com.app.library.adapters.input.rent.RentRequestDto;
import com.app.library.adapters.output.rent.RentResponseDto;
import com.app.library.domain.entity.rent.Rent;

public class RentDtoMapper {
    public RentResponseDto mapToResponse(Rent rent) {
        return new RentResponseDto(
                rent.getId(),
                rent.getBook_id(),
                rent.getEmit_date(),
                rent.getReturn_date(),
                rent.isReturned(),
                rent.getLibrarian_id(),
                rent.getMember_id()
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
}
