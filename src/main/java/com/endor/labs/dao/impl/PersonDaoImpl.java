package com.endor.labs.dao.impl;

import com.endor.labs.dao.PersonDao;
import com.endor.labs.model.Person;
import com.endor.labs.utils.Preconditions;
import com.endor.labs.utils.retry.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl extends AbstractDaoImpl<Person> implements PersonDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public PersonDaoImpl(MongoTemplate mongoTemplate) {
        super(mongoTemplate, Person.class);
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * It returns the Person record that matches the Name that we specified or else it returns null
     * @param argName
     * @return
     */
    @Override
    public Person findByName(String argName) {
        Preconditions.assertNotEmpty(argName, "Please provide a valid name to fetch the record: " + argName);
        Query query = new Query();
        query.addCriteria(Criteria.where(Person.FILTER_NAME).is(argName));
        return Retry.execute("recordById", () -> this.mongoTemplate.findOne(query, Person.class));
    }
}
