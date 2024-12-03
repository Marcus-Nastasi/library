package com.app.library.domain.entity.librarian;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class LibrarianPaginated implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int page;
    private int size;
    private int total;
    private List<Librarian> data;

    public LibrarianPaginated() {}

    public LibrarianPaginated(int page, int size, int total, List<Librarian> data) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Librarian> getData() {
        return data;
    }

    public void setData(List<Librarian> data) {
        this.data = data;
    }
}
