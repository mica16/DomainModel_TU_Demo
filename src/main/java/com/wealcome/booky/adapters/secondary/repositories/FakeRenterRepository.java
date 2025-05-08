package com.wealcome.booky.adapters.secondary.repositories;

import com.wealcome.booky.corelogic.models.Renter;
import com.wealcome.booky.corelogic.models.RenterRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeRenterRepository implements RenterRepository {

    private final Map<UUID, Renter> rentersByIds = new HashMap<>();

    @Override
    public Renter byId(UUID renterId) {
        return rentersByIds.get(renterId);
    }

    public void feedWith(Renter renter) {
        rentersByIds.put(renter.id(), renter);
    }
}
