package com.app.library.infrastructure.gateway.rent;

import com.app.library.application.gateways.rent.RentGateway;
import com.app.library.domain.entity.exception.DomainException;
import com.app.library.domain.entity.rent.Rent;
import com.app.library.domain.entity.rent.RentPaginated;
import com.app.library.infrastructure.mapper.rent.RentEntityMapper;
import com.app.library.infrastructure.persistence.rent.JpaRentRepo;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public class RentRepoGateway implements RentGateway {

    private final JpaRentRepo jpaRentRepo;
    private final RentEntityMapper rentEntityMapper;

    public RentRepoGateway(JpaRentRepo jpaRentRepo, RentEntityMapper rentEntityMapper) {
        this.jpaRentRepo = jpaRentRepo;
        this.rentEntityMapper = rentEntityMapper;
    }

    @Override
    public RentPaginated getAll(int page, int size) {
        return rentEntityMapper.mapToRentPaginated(jpaRentRepo.findAll(PageRequest.of(page, size)));
    }

    @Override
    public Rent get(UUID id) {
        return rentEntityMapper
            .mapFromRentEntity(jpaRentRepo.findById(id).orElseThrow(() -> new DomainException("Rent not found")));
    }

    @Override
    public Rent create(Rent rent) {
        return rentEntityMapper.mapFromRentEntity(jpaRentRepo.save(rentEntityMapper.mapToRentEntity(rent)));
    }

    @Override
    public Rent update(Rent rent) {
        return rentEntityMapper.mapFromRentEntity(jpaRentRepo.save(rentEntityMapper.mapToRentEntity(rent)));
    }

    @Override
    public Rent delete(UUID id) {
        Rent rent = rentEntityMapper.mapFromRentEntity(jpaRentRepo.findById(id).orElseThrow(() -> new DomainException("Rent not found")));
        jpaRentRepo.deleteById(id);
        return rent;
    }

    @Override
    public RentPaginated getByMember(UUID member_id, int page, int size) {
        return rentEntityMapper.mapToRentPaginated(jpaRentRepo.findByMemberId(member_id, PageRequest.of(page, size)));
    }

    @Override
    public RentPaginated getByBookId(UUID book_id, int page, int size) {
        return rentEntityMapper.mapToRentPaginated(jpaRentRepo.findByBookId(book_id, PageRequest.of(page, size)));
    }

    @Override
    public List<Rent> getAllByBookId(UUID book_id) {
        return jpaRentRepo.findAllByBookId(book_id).stream().map(rentEntityMapper::mapFromRentEntity).toList();
    }
}
