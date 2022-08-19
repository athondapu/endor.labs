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

    /**
     * This DAO method save the object into the MongoDB
     * @param argData
     * @return
     */
    @Override
    public T save(T argData) {
        Preconditions.assertNotNull(argData, "Input is null");
        return Retry.execute("save", () -> this.mongoTemplate.save(argData));
    }

    /**
     * This method tell you the count of the records in a collection that we specified
     * @param argQuery
     * @param argCollectionName
     * @return record count which represents the number of records that matches to the query that we specified
     */
    @Override
    public long count(Query argQuery, String argCollectionName) {
        return Retry.execute("save", () -> this.mongoTemplate.count(argQuery, argCollectionName));
    }

    /**
     * This method tell you the count of the records in a collection that we specified
     * @param argCollectionName
     * @return record count
     */
    @Override
    public long count(String argCollectionName) {
        return Retry.execute("save", () -> this.mongoTemplate.getCollection(argCollectionName).countDocuments());
    }

    /**
     * This method returns a record that matches the specified id
     * @param argId
     * @return Object which matches to the entity class
     */
    @Override
    public T getRecordById(String argId) {
        Preconditions.assertNotEmpty(argId, "Please provide a valid id to fetch the record: " + argId);
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(argId));
        return (T) Retry.execute("recordById", () -> this.mongoTemplate.findOne(query, entityClass));
    }

    /**
     * It returns the first record that matches to the query that we passed
     * @param argQuery
     * @return Object that matches to the entity class
     */
    @Override
    public T findOne(Query argQuery) {
        Preconditions.assertNotNull(argQuery, "Please pass a valid query to fetch the record: ");
        return (T) Retry.execute("findOne", () -> this.mongoTemplate.findOne(argQuery, entityClass));
    }

    /**
     * Returns the list of records that collection has
     * @return
     */
    @Override
    public List<T> listRecords() {
        return (List<T>) Retry.execute("listRecords", () -> this.mongoTemplate.findAll(this.entityClass));
    }

    /**
     * It deletes the record from that collection which matches that Id
     * @param argId
     * @return a deleted object
     */
    @Override
    public T deleteRecord(String argId) {
        Preconditions.assertNotEmpty(argId, "Please provide a valid id to delete the record: " + argId);
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(argId));
        return (T) Retry.execute("findOne", () -> this.mongoTemplate.findAndRemove(query, entityClass));
    }
}
