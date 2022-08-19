package com.endor.labs.controllers;

import com.endor.labs.dao.impl.PersonDaoImpl;
import com.endor.labs.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonDaoImpl personDaoImpl;

    @PostMapping("/")
    @ResponseBody
    public Person save(@RequestBody Person person) {
        Person result = personDaoImpl.save(person);
        return result;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Person findById(@PathVariable String id) {
        Person result = personDaoImpl.getRecordById(id);
        return result;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Person> list() {
        List<Person> results = personDaoImpl.listRecords();
        return results;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Person delete(@PathVariable String id) {
        Person result = personDaoImpl.deleteRecord(id);
        return result;
    }
}
