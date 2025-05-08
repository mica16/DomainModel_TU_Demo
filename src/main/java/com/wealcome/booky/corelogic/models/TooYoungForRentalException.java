package com.wealcome.booky.corelogic.models;

public class TooYoungForRentalException extends RuntimeException {
    public TooYoungForRentalException(String message) {
        super(message);
    }
}
