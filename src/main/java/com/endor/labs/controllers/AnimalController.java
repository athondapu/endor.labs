package com.endor.labs.controllers;

import com.endor.labs.dao.impl.AnimalDaoImpl;
import com.endor.labs.dao.impl.PersonDaoImpl;
import com.endor.labs.model.Animal;
import com.endor.labs.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalDaoImpl animalDaoImpl;

    /**
     * THis method saves the animal data into the MongoDB
     * @param animal
     * @return Animal object, and it has all the fields like id, name and kind
     */
    @PostMapping("/")
    @ResponseBody
    public Animal save(@RequestBody Animal animal) {
        Animal result = animalDaoImpl.save(animal);
        return result;
    }

    /**
     * This method pulls the record by id
     * @param id
     * @return Animal object which matches that id
     */
    @RequestMapping("/{id}")
    @ResponseBody
    public Animal findById(@PathVariable String id) {
        Animal result = animalDaoImpl.getRecordById(id);
        return result;
    }

    /**
     * It lists down all the animal records
     * @return list of animal records
     */
    @GetMapping("/")
    @ResponseBody
    public List<Animal> list() {
        List<Animal> results = animalDaoImpl.listRecords();
        return results;
    }

    /**
     * It deletes the entry from the database by id
     * @param id
     * @return It returns the deleted record
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Animal delete(@PathVariable String id) {
        Animal result = animalDaoImpl.deleteRecord(id);
        return result;
    }
}
