package com.endor.labs.dao;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface GenericDao<T> {

    T save(T argData);

    long count(Query argQuery,  String argCollectionName);

    long count(String argCollectionName);

    T getRecordById(String argId);

    T findOne(Query argQuery);

    List<T> listRecords();

    T deleteRecord(String argId);
}
