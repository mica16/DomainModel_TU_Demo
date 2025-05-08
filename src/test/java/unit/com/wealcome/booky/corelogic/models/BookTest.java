package unit.com.wealcome.booky.corelogic.models;

import com.wealcome.booky.corelogic.models.Book;
import com.wealcome.booky.corelogic.models.TooYoungForRentalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookTest {

    private final UUID bookId = UUID.fromString("fe2b0c4a-1f3d-4b8c-9f5d-7a2e6f3b5c1b");
    private final UUID renterId = UUID.fromString("ab2c0d4a-1f3d-4b8c-9f5d-7a2e6f3b5c1b");
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book(bookId, 10);
    }

    @Test
    public void can_rent_if_minimum_age() {
        book.rent(renterId, 10, LocalDateTime.now());
        assertThat(book.isNotAvailableForRental()).isTrue();
    }

    @Test
    public void cannot_rent_if_less_than_minimum_age() {
        assertThrows(
                TooYoungForRentalException.class,
                () -> book.rent(renterId, 9, LocalDateTime.now())
        );
    }

}
