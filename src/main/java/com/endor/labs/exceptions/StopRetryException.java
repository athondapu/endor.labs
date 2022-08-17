package com.endor.labs.exceptions;

public class StopRetryException extends RetryException{

    private static final long serialVersionUID = -4316630574878864210L;

    public StopRetryException(final String msg) {
        super(msg);
    }

    public StopRetryException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
