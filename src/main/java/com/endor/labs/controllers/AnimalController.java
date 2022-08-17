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

    @PostMapping("/")
    @ResponseBody
    public Animal save(@RequestBody Animal animal) {
        Animal result = animalDaoImpl.save(animal);
        return result;
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public Animal findById(@PathVariable String id) {
        Animal result = animalDaoImpl.getRecordById(id);
        return result;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Animal> list() {
        List<Animal> results = animalDaoImpl.listRecords();
        return results;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Animal delete(@PathVariable String id) {
        Animal result = animalDaoImpl.deleteRecord(id);
        return result;
    }
}
