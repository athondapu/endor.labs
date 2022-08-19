package com.endor.labs.dao.impl;

import com.endor.labs.dao.AnimalDao;
import com.endor.labs.dao.PersonDao;
import com.endor.labs.model.Animal;
import com.endor.labs.model.Person;
import com.endor.labs.utils.Preconditions;
import com.endor.labs.utils.retry.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AnimalDaoImpl extends AbstractDaoImpl<Animal> implements AnimalDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AnimalDaoImpl(MongoTemplate mongoTemplate) {
        super(mongoTemplate, Animal.class);
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * It returns the Animal record that matches the Name that we specified or else it returns null
     * @param argName
     * @return
     */
    @Override
    public Animal findByName(String argName) {
        Preconditions.assertNotEmpty(argName, "Please provide a valid name to fetch the record: " + argName);
        Query query = new Query();
        query.addCriteria(Criteria.where(Animal.FILTER_NAME).is(argName));
        return Retry.execute("recordById", () -> this.mongoTemplate.findOne(query, Animal.class));
    }
}
