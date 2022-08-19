package com.endor.labs.exceptions;

/**
 * This exception will throw when ever we exhausted all the retires
 */
public class MaximumRetryException extends RuntimeException {
    private static final long serialVersionUID = -2992310194137994594L;

    public MaximumRetryException(final String msg) {
        super(msg);
    }

    public MaximumRetryException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
