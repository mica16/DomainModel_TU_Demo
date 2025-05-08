package com.wealcome.booky.corelogic.models;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

public record Renter(@Getter UUID id, @Getter LocalDate dateOfBirth) {

}
