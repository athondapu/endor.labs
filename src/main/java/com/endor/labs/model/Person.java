package com.endor.labs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "item")
public class Person {

    public static final String FILTER_ID = "id";
    public static final String FILTER_NAME = "name";

    @Id
    private String id;

    @Indexed(background = true)
    private String name;

    private String kind;

    public String getId() {
        return id;
    }

    public Person setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }
    public String getKind() {
        return kind;
    }
    public Person setKind(String kind) {
        this.kind = kind;
        return this;
    }
}
