package com.app.library.infrastructure.configuration.librarian;

import com.app.library.adapters.mapper.librarian.LibrarianDtoMapper;
import com.app.library.application.gateways.librarian.LibrarianGateway;
import com.app.library.application.usecases.librarian.LibrarianUseCase;
import com.app.library.application.usecases.security.PasswordUseCase;
import com.app.library.infrastructure.gateway.librarian.LibrarianRepoGateway;
import com.app.library.infrastructure.mapper.librarian.LibrarianEntityMapper;
import com.app.library.infrastructure.persistence.librarian.JpaLibrarianRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibrarianBeans {

    @Bean
    public LibrarianUseCase librarianUseCase(LibrarianGateway librarianGateway, PasswordUseCase passwordUseCase) {
        return new LibrarianUseCase(librarianGateway, passwordUseCase);
    }

    @Bean
    public LibrarianGateway librarianGateway(JpaLibrarianRepo jpaLibrarianRepo, LibrarianEntityMapper librarianEntityMapper) {
        return new LibrarianRepoGateway(jpaLibrarianRepo, librarianEntityMapper);
    }

    @Bean
    public LibrarianDtoMapper librarianDtoMapper() {
        return new LibrarianDtoMapper();
    }

    @Bean
    public LibrarianEntityMapper librarianEntityMapper() {
        return new LibrarianEntityMapper();
    }
}
