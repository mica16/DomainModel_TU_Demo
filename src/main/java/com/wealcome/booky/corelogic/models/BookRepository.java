package com.wealcome.booky.corelogic.models;

import java.util.UUID;

public interface BookRepository {
    Book byId(UUID bookId);
}
