package com.wealcome.booky.corelogic.models;

import java.util.UUID;

public interface RenterRepository {
    Renter byId(UUID renterId);
}
