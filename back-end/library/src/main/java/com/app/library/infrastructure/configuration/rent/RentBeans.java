package com.app.library.infrastructure.configuration.rent;

import com.app.library.adapters.mapper.rent.RentDtoMapper;
import com.app.library.application.gateways.rent.RentGateway;
import com.app.library.application.usecases.book.BookUseCase;
import com.app.library.application.usecases.rent.RentUseCase;
import com.app.library.infrastructure.gateway.rent.RentRepoGateway;
import com.app.library.infrastructure.mapper.rent.RentEntityMapper;
import com.app.library.infrastructure.persistence.rent.JpaRentRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentBeans {

    @Bean
    public RentUseCase rentUseCase(RentGateway rentGateway, BookUseCase bookUseCase) {
        return new RentUseCase(rentGateway, bookUseCase);
    }

    @Bean
    public RentGateway rentGateway(JpaRentRepo jpaRentRepo, RentEntityMapper rentEntityMapper) {
        return new RentRepoGateway(jpaRentRepo, rentEntityMapper);
    }

    @Bean
    public RentDtoMapper rentDtoMapper() {
        return new RentDtoMapper();
    }

    @Bean
    public RentEntityMapper rentEntityMapper() {
        return new RentEntityMapper();
    }
}
