package com.app.library.domain.entity.librarian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Librarian {
    private UUID id;
    private String name;
    private String cpf;
    private String password;
    private UserRole role;

    public Librarian updateDetails(Librarian updatedLibrarian) {
        if (updatedLibrarian.name == null) throw new IllegalArgumentException("Name cannot be null");
        if (updatedLibrarian.password == null) throw new IllegalArgumentException("Password cannot be null");
        this.setId(updatedLibrarian.getId());
        this.setName(updatedLibrarian.getName());
        this.setPassword(updatedLibrarian.getPassword());
        this.setCpf(updatedLibrarian.getCpf());
        this.setRole(updatedLibrarian.getRole());
        return updatedLibrarian;
    }
}
