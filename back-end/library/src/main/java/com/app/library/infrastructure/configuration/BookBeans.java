package com.app.library.infrastructure.configuration;

import com.app.library.application.gateways.book.BookGateway;
import com.app.library.application.usecases.book.BookUseCase;
import com.app.library.infrastructure.gateway.book.BookRepoGateway;
import com.app.library.infrastructure.mappers.book.BookEntityMapper;
import com.app.library.infrastructure.persistence.book.JpaBookRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookBeans {

    @Bean
    public BookUseCase bookUseCase(BookGateway bookGateway) {
        return new BookUseCase(bookGateway);
    }

    @Bean
    public BookGateway bookGateway(JpaBookRepo jpaBookRepo, BookEntityMapper bookEntityMapper) {
        return new BookRepoGateway(jpaBookRepo, bookEntityMapper);
    }

    @Bean
    public BookEntityMapper bookEntityMapper() {
        return new BookEntityMapper();
    }
}
