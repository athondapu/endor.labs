package com.endor.labs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "item")
public class Animal {

    public static final String FILTER_ID = "id";
    public static final String FILTER_NAME = "name";

    public static final String FILTER_KIND = "kind";
    @Id
    private String id;

    @Indexed(background = true)
    private String name;

    private String kind;

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

    public String getKind() {
        return kind;
    }

    public Animal setKind(String kind) {
        this.kind = kind;
        return this;
    }
}
