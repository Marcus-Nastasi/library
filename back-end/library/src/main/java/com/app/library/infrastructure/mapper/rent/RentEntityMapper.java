package com.app.library.infrastructure.mapper.rent;

import com.app.library.domain.entity.rent.Rent;
import com.app.library.domain.entity.rent.RentPaginated;
import com.app.library.infrastructure.entity.rent.RentEntity;
import org.springframework.data.domain.Page;

public class RentEntityMapper {

    public RentEntity mapToRentEntity(Rent rent) {
        return new RentEntity(
            rent.getId(),
            rent.getBook_id(),
            rent.getEmit_date(),
            rent.getReturn_date(),
            rent.isReturned(),
            rent.getLibrarian_id(),
            rent.getMember_id()
        );
    }

    public Rent mapFromRentEntity(RentEntity rent) {
        return new Rent(
            rent.getId(),
            rent.getBookId(),
            rent.getEmit_date(),
            rent.getReturn_date(),
            rent.getLibrarianId(),
            rent.getMemberId(),
            rent.isReturned()
        );
    }

    public RentPaginated mapToRentPaginated(Page<RentEntity> rentEntities) {
        return new RentPaginated(
            rentEntities.getNumber(),
            rentEntities.getSize(),
            rentEntities.getTotalPages(),
            rentEntities.map(this::mapFromRentEntity).toList()
        );
    }
}
