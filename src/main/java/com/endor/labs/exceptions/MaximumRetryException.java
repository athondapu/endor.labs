package com.endor.labs.exceptions;

public class MaximumRetryException extends RetryException
{
    private static final long serialVersionUID = -2992310194137994594L;

    public MaximumRetryException(final String msg) {
        super(msg);
    }

    public MaximumRetryException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
