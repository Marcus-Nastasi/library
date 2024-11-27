package com.app.library.domain.entity.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookPaginated {
    private int page;
    private int size;
    private int total;
    private List<Book> data;
}
