package com.wealcome.booky.corelogic.models;

public class BookNotAvailableException extends RuntimeException {

    public BookNotAvailableException(String message) {
        super(message);
    }
}
