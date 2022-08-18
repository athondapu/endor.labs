package com.endor.labs.dao;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface GenericDao<T> {

    T save(T argData);

    long count(Query argQuery, Class<?> argClass);

    long countByCollectionName(String argCollectionName);

    T getRecordById(String argId);

    T findOne(Query argQuery);

    List<T> listRecords();

    T deleteRecord(String argId);
}
