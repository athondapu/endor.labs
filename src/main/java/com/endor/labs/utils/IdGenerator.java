package com.endor.labs.utils;

import java.util.UUID;

public class IdGenerator {

    /**
     * We are using default UUID functionality to generate ID.
     * @return
     */
    // TODO we can improve this id generation functionality to avoid duplicates.
    // While generating we can consider the time, machine identifier, process identifier
    // so that we can generate unique number across the pods
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }
}
