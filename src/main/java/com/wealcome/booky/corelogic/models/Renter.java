package com.wealcome.booky.corelogic.models;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

public final class Renter {
    @Getter
    private final UUID id;
    @Getter
    private final LocalDate dateOfBirth;

    public Renter(UUID id, LocalDate dateOfBirth) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
    }

}
