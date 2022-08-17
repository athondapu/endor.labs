package com.endor.labs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "animal")
public class Animal {

    public static final String FILTER_ID = "id";
    public static final String FILTER_NAME = "name";
    @Id
    private String id;

    @Indexed(background = true)
    private String name;

    public String getId() {
        return id;
    }

    public Animal setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Animal setName(String name) {
        this.name = name;
        return this;
    }
}
