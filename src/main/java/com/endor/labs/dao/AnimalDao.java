package com.endor.labs.dao;

import com.endor.labs.model.Animal;

public interface AnimalDao {

    /**
     * Find Animal record by animal name
     * @param argName
     * @return
     */
    Animal findByName(String argName);
}
