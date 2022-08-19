package com.endor.labs.dao;

import com.endor.labs.model.Person;

public interface PersonDao {

    /**
     * Find Person record by animal name
     * @param argName
     * @return
     */
    Person findByName(String argName);
}
