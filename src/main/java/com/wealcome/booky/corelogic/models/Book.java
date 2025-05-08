package com.wealcome.booky.corelogic.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode
public class Book {

    @Getter
    private final UUID id;
    private final int minimumAge;
    private Rental currentRental;

    public Book(UUID id, int minimumAge) {
        this(id, minimumAge, null);
    }

    public Book(UUID id, int minimumAge, Rental currentRental) {
        this.id = id;
        this.minimumAge = minimumAge;
        this.currentRental = currentRental;
    }

    public void rent(UUID renterId, int renterAge, LocalDateTime currentDateTime) {
        if (renterAge < minimumAge)
            throw new TooYoungForRentalException("Renter is too young");
        currentRental = new Rental(renterId, currentDateTime);
    }

    public boolean isNotAvailableForRental() {
        return currentRental != null;
    }
}
