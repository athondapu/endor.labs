package com.endor.labs.dao;

import com.endor.labs.model.Person;

public interface PersonDao {

    Person findByName(String argName);
}
