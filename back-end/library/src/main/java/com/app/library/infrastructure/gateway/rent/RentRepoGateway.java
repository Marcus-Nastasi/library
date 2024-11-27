package com.app.library.infrastructure.gateway.rent;

import com.app.library.application.gateways.rent.RentGateway;
import com.app.library.domain.entity.rent.Rent;
import com.app.library.infrastructure.mappers.rent.RentEntityMapper;
import com.app.library.infrastructure.persistence.rent.JpaRentRepo;

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
    public List<Rent> getAll() {
        return jpaRentRepo.findAll().stream().map(rentEntityMapper::mapFromRentEntity).toList();
    }

    @Override
    public Rent get(UUID id) {
        return rentEntityMapper.mapFromRentEntity(jpaRentRepo.findById(id).orElseThrow());
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
        Rent rent = rentEntityMapper.mapFromRentEntity(jpaRentRepo.findById(id).orElseThrow());
        jpaRentRepo.deleteById(id);
        return rent;
    }
}
