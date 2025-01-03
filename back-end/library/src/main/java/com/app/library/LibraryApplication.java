package com.app.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackages = "com.app.library.infrastructure.persistence")
public class LibraryApplication {

    public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
}
