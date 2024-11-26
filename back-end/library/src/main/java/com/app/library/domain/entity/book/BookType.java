package com.app.library.domain.entity.book;

public enum BookType {
    REGULAR("regular"),
    STUDY("study"),
    JOURNAL("jornal"),
    MAGAZINE("magazine");

    private final String bookStatus;

    BookType(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBookStatus() {
        return bookStatus;
    }
}
