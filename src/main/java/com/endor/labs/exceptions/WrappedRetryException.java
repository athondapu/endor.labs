package com.endor.labs.exceptions;

public class WrappedRetryException extends RetryException
{
    private final Object object;

    public WrappedRetryException(final String msg, final Object object) {
        super(msg);
        this.object = object;
    }

    public WrappedRetryException(final String msg, final Throwable cause, final Object object) {
        super(msg, cause);
        this.object = object;
    }

    public WrappedRetryException(final Throwable cause, final Object object) {
        super(cause);
        this.object = object;
    }

    public <T> T getObject() {
        return (T)this.object;
    }
}
