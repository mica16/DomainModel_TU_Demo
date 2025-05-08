package com.wealcome.booky.adapters.secondary.repositories;

import com.wealcome.booky.corelogic.models.Book;
import com.wealcome.booky.corelogic.models.BookRepository;

import java.util.*;

public class FakeBookRepository implements BookRepository {

    private final Map<UUID, Book> booksByIds = new HashMap<>();

    @Override
    public Book byId(UUID bookId) {
        return booksByIds.get(bookId);
    }

    public Collection<Book> all() {
        return booksByIds.values();
    }

    public void feedWith(Book book) {
        booksByIds.put(book.getId(), book);
    }
}
