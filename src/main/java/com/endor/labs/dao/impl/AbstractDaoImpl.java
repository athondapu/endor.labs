package com.endor.labs.dao.impl;

import com.endor.labs.dao.GenericDao;
import com.endor.labs.utils.Preconditions;
import com.endor.labs.utils.retry.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public abstract class AbstractDaoImpl<T> implements GenericDao<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDaoImpl.class);
    public static final String ID = "id";
    private final MongoTemplate mongoTemplate;
    private final Class<?> entityClass;

    public AbstractDaoImpl(MongoTemplate argMongoTemplate, Class<?> argClass) {
        this.mongoTemplate = argMongoTemplate;
        this.entityClass = argClass;
    }

    @Override
    public T save(T argData) {
        Preconditions.assertNotNull(argData, "Input is null");
        return Retry.execute("save", () -> this.mongoTemplate.save(argData));
    }

    @Override
    public long count(Query argQuery, Class<?> argClass) {
        return Retry.execute("save", () -> this.mongoTemplate.count(argQuery, argClass));
    }

    @Override
    public long countByCollectionName(String argCollectionName) {
        return Retry.execute("save", () -> this.mongoTemplate.getCollection(argCollectionName).countDocuments());
    }

    @Override
    public T getRecordById(String argId) {
        Preconditions.assertNotEmpty(argId, "Please provide a valid id to fetch the record: " + argId);
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(argId));
        return (T) Retry.execute("recordById", () -> this.mongoTemplate.findOne(query, entityClass));
    }

    @Override
    public T findOne(Query argQuery) {
        Preconditions.assertNotNull(argQuery, "Please pass a valid query to fetch the record: ");
        return (T) Retry.execute("findOne", () -> this.mongoTemplate.findOne(argQuery, entityClass));
    }

    @Override
    public List<T> listRecords() {
        return (List<T>) Retry.execute("listRecords", () -> this.mongoTemplate.findAll(this.entityClass));
    }

    @Override
    public T deleteRecord(String argId) {
        Preconditions.assertNotEmpty(argId, "Please provide a valid id to delete the record: " + argId);
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(argId));
        return (T) Retry.execute("findOne", () -> this.mongoTemplate.findAndRemove(query, entityClass));
    }
}
