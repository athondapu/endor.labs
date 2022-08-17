package com.endor.labs.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(final String msg) {
        super(msg);
    }

    public DatabaseException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public DatabaseException(final Throwable cause) {
        super(cause);
    }
}