package com.wealcome.booky.corelogic.usecases.bookrental;

import com.wealcome.booky.corelogic.models.BookNotAvailableException;
import com.wealcome.booky.corelogic.models.BookRepository;
import com.wealcome.booky.corelogic.models.DateTimeProvider;
import com.wealcome.booky.corelogic.models.RenterRepository;
import jakarta.transaction.Transactional;

@Transactional
public class RentBookCommandHandler {

    private final RenterRepository renterRepository;
    private final BookRepository bookRepository;
    private final DateTimeProvider dateTimeProvider;

    public RentBookCommandHandler(RenterRepository renterRepository,
                                  BookRepository bookRepository,
                                  DateTimeProvider dateTimeProvider) {
        this.renterRepository = renterRepository;
        this.bookRepository = bookRepository;
        this.dateTimeProvider = dateTimeProvider;
    }

    public void handleRentBookCommand(RentBookCommand command) {
        var renter = renterRepository.byId(command.renterId());
        var book = bookRepository.byId(command.bookId());

        var renterAge = (int) renter.getDateOfBirth().until(
                dateTimeProvider.now().toLocalDate(),
                java.time.temporal.ChronoUnit.YEARS
        );

        if (book.isNotAvailableForRental())
            throw new BookNotAvailableException("Book is not available for rental");
        book.rent(renter.getId(), renterAge, dateTimeProvider.now());
    }

}
