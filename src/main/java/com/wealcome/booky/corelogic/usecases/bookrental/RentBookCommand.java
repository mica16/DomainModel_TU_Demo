package com.wealcome.booky.corelogic.usecases.bookrental;

import java.util.UUID;

public record RentBookCommand(UUID renterId, UUID bookId) {
}
