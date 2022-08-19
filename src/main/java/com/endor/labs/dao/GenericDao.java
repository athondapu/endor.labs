package com.endor.labs.dao;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Generic DAO has list of generic methods that we can reuse in any DAO implementations
 * @param <T>
 */
public interface GenericDao<T> {

    T save(T argData);

    long count(Query argQuery,  String argCollectionName);

    long count(String argCollectionName);

    T getRecordById(String argId);

    T findOne(Query argQuery);

    List<T> listRecords();

    T deleteRecord(String argId);
}
