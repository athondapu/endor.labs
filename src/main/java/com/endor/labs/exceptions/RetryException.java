package com.endor.labs.exceptions;

public class RetryException extends RuntimeException
{
    private static final long serialVersionUID = 799100857061564750L;

    public RetryException(final String msg) {
        super(msg);
    }

    public RetryException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public RetryException(final Throwable cause) {
        super(cause);
    }
}