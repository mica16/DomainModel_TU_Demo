package com.wealcome.booky.corelogic.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record Rental(UUID renterId, LocalDateTime rentedOn) {

}
