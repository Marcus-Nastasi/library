package com.app.library.infrastructure.configuration.book;

import com.app.library.adapters.mapper.book.BookDtoMapper;
import com.app.library.application.gateways.book.BookGateway;
import com.app.library.application.gateways.rent.RentGateway;
import com.app.library.application.usecases.aws.FileManagerUseCase;
import com.app.library.application.usecases.book.BookUseCase;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.infrastructure.gateway.book.BookRepoGateway;
import com.app.library.infrastructure.mapper.book.BookEntityMapper;
import com.app.library.infrastructure.persistence.book.JpaBookRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookBeans {

    @Bean
    public BookUseCase bookUseCase(BookGateway bookGateway, FileManagerUseCase fileManager, RentGateway rentGateway, MemberUseCase memberUseCase) {
        return new BookUseCase(bookGateway, fileManager, rentGateway, memberUseCase);
    }

    @Bean
    public BookGateway bookGateway(JpaBookRepo jpaBookRepo, BookEntityMapper bookEntityMapper) {
        return new BookRepoGateway(jpaBookRepo, bookEntityMapper);
    }

    @Bean
    public BookDtoMapper bookDtoMapper() {
        return new BookDtoMapper();
    }

    @Bean
    public BookEntityMapper bookEntityMapper() {
        return new BookEntityMapper();
    }
}
