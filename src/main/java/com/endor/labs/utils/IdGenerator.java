package com.endor.labs.utils;

import java.util.UUID;

public class IdGenerator {

    public static String getUuid() {
        return UUID.randomUUID().toString();
    }
}
