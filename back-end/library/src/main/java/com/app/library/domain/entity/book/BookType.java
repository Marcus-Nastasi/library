package com.app.library.domain.entity.book;

public enum BookType {
    REGULAR("regular"),
    STUDY("study"),
    JOURNAL("jornal"),
    MAGAZINE("magazine");

    private final String bookType;

    BookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookType() {
        return bookType;
    }
}
