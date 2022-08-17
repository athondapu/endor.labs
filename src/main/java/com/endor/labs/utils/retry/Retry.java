package com.endor.labs.utils.retry;

import com.endor.labs.exceptions.MaximumRetryException;
import com.endor.labs.exceptions.StopRetryException;
import com.endor.labs.exceptions.WrappedRetryException;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Retry {
    protected static final Logger logger = LoggerFactory.getLogger((Class) Retry.class);
    private static final int MAX_RETRIES = 8;
    private static final long MAX_WAIT_MILLIS = 1000L;

    public static <T> T execute(final int numRetries, final long waitMillis, final String taskName, final Retryable<T> retryable) {
        Preconditions.checkArgument(0 < numRetries, "Invalid number of retires {}. Should be 0 < numRetries", numRetries);
        Preconditions.checkArgument(0L < waitMillis, "Invalid delay time {}ms. Should be 0ms < waitMillis <= {}ms.", waitMillis, 1000L);
        int tries = 0;
        int backoff = 1;
        Exception ex = null;
        for (boolean continueRetrying = true; tries < numRetries && continueRetrying; ++tries) {
            try {
                return retryable.retry();
            } catch (StopRetryException e) {
                ex = e;
                continueRetrying = false;
            } catch (Exception e2) {
                final long delayMillis = waitMillis * backoff;
                Retry.logger.warn("Retrying: {} {}/{} after sleeping {}ms due to exception: {}", new Object[]{taskName, tries, numRetries, delayMillis, e2.getMessage()});
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ex2) {
                }
                backoff <<= 1;
                ex = e2;
            }
        }
        if (ex instanceof StopRetryException) {
            throw (StopRetryException) ex;
        }
        throw new MaximumRetryException("Reached maximum retries" + numRetries, (Throwable) ex);
    }

    public static <T> T execute(final RetryOptions retryOptions, final Retryable<T> retryable) {
        final int numRetries = retryOptions.getRetries();
        final long waitMillis = retryOptions.getWaitMillis();
        Preconditions.checkArgument(0 < numRetries, "Invalid number of retires {}. Should be 0 < numRetries", numRetries);
        Preconditions.checkArgument(0L < waitMillis, "Invalid delay time {}ms. Should be 0ms < waitMillis <= {}ms.", waitMillis, 1000L);
        int tries = 0;
        int backoff = 1;
        Exception ex = null;
        for (boolean continueRetrying = true; tries < numRetries && continueRetrying; ++tries) {
            try {
                return retryable.retry();
            } catch (StopRetryException e) {
                ex = e;
                continueRetrying = false;
            } catch (Exception e2) {
                final long delayMillis = waitMillis * backoff;
                Retry.logger.warn("Retrying: {} {}/{} after sleeping {}ms due to exception: {}", new Object[]{retryOptions.getName(), tries, numRetries, delayMillis, e2.getMessage()});
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ex2) {
                }
                backoff <<= 1;
                ex = e2;
            }
        }
        if (ex instanceof StopRetryException) {
            throw (StopRetryException) ex;
        }
        if (retryOptions.isThrowException()) {
            throw new MaximumRetryException("Reached maximum retries: " + numRetries, (Throwable) ex);
        }
        if (ex instanceof WrappedRetryException) {
            return ((WrappedRetryException) ex).getObject();
        }
        throw new IllegalStateException("" + retryOptions, (Throwable) ex);
    }

    public static <T> T execute(String taskName, Retryable<T> argRetryable) {
        return execute(MAX_RETRIES, MAX_WAIT_MILLIS, taskName, argRetryable);
    }

    public static final class RetryOptions {
        private final int retries;
        private final long waitMillis;
        private final String name;
        private final boolean throwException;

        public RetryOptions(final int retries, final long waitMillis, final String name, final boolean throwException) {
            this.retries = retries;
            this.waitMillis = waitMillis;
            this.name = name;
            this.throwException = throwException;
        }

        public RetryOptions(final int retries, final long waitMillis, final String name) {
            this(retries, waitMillis, name, true);
        }

        public int getRetries() {
            return this.retries;
        }

        public long getWaitMillis() {
            return this.waitMillis;
        }

        public String getName() {
            return this.name;
        }

        public boolean isThrowException() {
            return this.throwException;
        }
    }
}