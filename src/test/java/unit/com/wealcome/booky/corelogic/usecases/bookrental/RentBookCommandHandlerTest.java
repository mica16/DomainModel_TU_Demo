package unit.com.wealcome.booky.corelogic.usecases.bookrental;

import com.wealcome.booky.adapters.secondary.repositories.FakeBookRepository;
import com.wealcome.booky.adapters.secondary.repositories.FakeRenterRepository;
import com.wealcome.booky.corelogic.models.*;
import com.wealcome.booky.corelogic.usecases.bookrental.RentBookCommand;
import com.wealcome.booky.corelogic.usecases.bookrental.RentBookCommandHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RentBookCommandHandlerTest {

    private final FakeBookRepository bookRepository = new FakeBookRepository();
    private final FakeRenterRepository renterRepository = new FakeRenterRepository();
    private final DeterministicDateTimeProvider dateTimeProvider = new DeterministicDateTimeProvider();
    private final RentBookCommandHandler rentBookCommandHandler = new RentBookCommandHandler(
            renterRepository,
            bookRepository,
            dateTimeProvider
    );
    private final UUID bookId = UUID.fromString("fe2b0c4a-1f3d-4b8c-9f5d-7a2e6f3b5c1b");
    private final UUID renterId = UUID.fromString("ab2c0d4a-1f3d-4b8c-9f5d-7a2e6f3b5c1b");

    @BeforeEach
    public void setUp() {
        var renter = new Renter(renterId, LocalDate.of(2000, 1, 1));
        renterRepository.feedWith(renter);
        dateTimeProvider.currentDateTime = LocalDateTime.of(2010, 10, 1, 10, 0);
    }

    @Nested
    public class BookIsAvailableForRental {

        @BeforeEach
        public void setUp() {
            var book = new Book(bookId, 10);
            bookRepository.feedWith(book);
        }

        @Test
        public void can_rent_it() {

            rentBookCommandHandler.handleRentBookCommand(
                    new RentBookCommand(renterId, bookId)
            );

            assertThat(bookRepository.all()).containsExactly(
                    new Book(bookId, 10,
                            new Rental(renterId,
                                    LocalDateTime.of(2010, 10, 1, 10, 0)))
            );
        }


        @Test
        public void cannot_rent_it_if_renter_has_not_the_minimum_age() {
            dateTimeProvider.currentDateTime = LocalDateTime.of(2009, 10, 1, 10, 0);
            assertThrows(TooYoungForRentalException.class, () ->
                    rentBookCommandHandler.handleRentBookCommand(
                            new RentBookCommand(renterId, bookId)
                    )
            );
        }
    }

    @Nested
    public class BookIsAlreadyRented {

        @BeforeEach
        public void setUp() {
            var book = new Book(bookId, 10,
                    new Rental(renterId, LocalDateTime.of(2010, 8, 1, 10, 0)));
            bookRepository.feedWith(book);
        }

        @Test
        public void cannot_rent_it() {
            assertThrows(BookNotAvailableException.class, () ->
                    rentBookCommandHandler.handleRentBookCommand(
                            new RentBookCommand(renterId, bookId)
                    )
            );
        }
    }

}
