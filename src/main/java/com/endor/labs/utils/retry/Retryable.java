package com.endor.labs.utils.retry;

@FunctionalInterface
public interface Retryable<T>
{
    T retry();
}