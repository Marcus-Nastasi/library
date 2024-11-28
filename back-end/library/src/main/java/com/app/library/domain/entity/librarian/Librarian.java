package com.app.library.domain.entity.librarian;

import java.util.UUID;

public class Librarian {
    private UUID id;
    private String name;
    private String cpf;
    private String password;
    private UserRole role;

    public Librarian updateDetails(Librarian updatedLibrarian) {
        if (updatedLibrarian.cpf == null) throw new IllegalArgumentException("Cpf cannot be null");
        if (updatedLibrarian.password == null) throw new IllegalArgumentException("Password cannot be null");
        this.setId(updatedLibrarian.getId());
        this.setName(updatedLibrarian.getName());
        this.setPassword(updatedLibrarian.getPassword());
        this.setCpf(updatedLibrarian.getCpf());
        this.setRole(updatedLibrarian.getRole());
        return updatedLibrarian;
    }

    public Librarian() {}

    public Librarian(UUID id, String name, String cpf, String password, UserRole role) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
