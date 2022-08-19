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

    /**
     * THis method saves the person data into the MongoDB
     * @param person
     * @return Person object, and it has all the fields like id, name and kind
     */
    @PostMapping("/")
    @ResponseBody
    public Person save(@RequestBody Person person) {
        Person result = personDaoImpl.save(person);
        return result;
    }

    /**
     * This method pulls the record by id
     * @param id
     * @return Person object which matches that id
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Person findById(@PathVariable String id) {
        Person result = personDaoImpl.getRecordById(id);
        return result;
    }

    /**
     * It lists down all the person records
     * @return list of person records
     */
    @GetMapping("/")
    @ResponseBody
    public List<Person> list() {
        List<Person> results = personDaoImpl.listRecords();
        return results;
    }

    /**
     * It deletes the entry from the database by id
     * @param id
     * @return It returns the deleted record
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Person delete(@PathVariable String id) {
        Person result = personDaoImpl.deleteRecord(id);
        return result;
    }
}
