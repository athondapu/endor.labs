package com.endor.labs.controllers;

import com.endor.labs.dao.impl.PersonDaoImpl;
import com.endor.labs.model.Person;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonDaoImplTest {

    @Autowired
    private PersonDaoImpl personDaoImpl;

    @Order(1)
    @Test
    public void savePerson() {
        Person person = new Person().setName("Endor").setId("123");
        Person result = this.personDaoImpl.save(person);
        assertThat(person.getName()).isEqualTo(result.getName());
    }

    @Order(2)
    @Test
    public void getPerson() {
        Person result = this.personDaoImpl.getRecordById("123");
        assertThat("123").isEqualTo(result.getId());
    }

    @Order(3)
    @Test
    public void count() {
        long count = this.personDaoImpl.count(null, Person.class);
        assertThat(1).isEqualTo(count);
    }

    @Order(4)
    @Test
    public void delete() {
        Person result = this.personDaoImpl.deleteRecord("123");
        long count = this.personDaoImpl.count(null, Person.class);
        assertThat(0).isEqualTo(count);
    }
}
