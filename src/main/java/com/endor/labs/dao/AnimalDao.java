package com.endor.labs.dao;

import com.endor.labs.model.Animal;

public interface AnimalDao {

    Animal findByName(String argName);
}
