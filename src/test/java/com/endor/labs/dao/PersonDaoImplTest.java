package com.endor.labs.dao;

import com.endor.labs.dao.impl.PersonDaoImpl;
import com.endor.labs.model.Animal;
import com.endor.labs.model.Person;
import com.endor.labs.utils.IdGenerator;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonDaoImplTest {

    @Autowired
    private PersonDaoImpl personDaoImpl;

    /**
     * Declaring the id here as I am testing all CRUD operations with only one record.
     * This can be change if we start testing with multiple records (We can avoid storing the id).
     */
    private static final String id = IdGenerator.getUuid();

    @Order(1)
    @Test
    public void savePerson() {
        Person person = new Person().setName("Endor").setId(this.id).setKind(Person.class.getSimpleName());
        Person result = this.personDaoImpl.save(person);
        assertThat(person.getName()).isEqualTo(result.getName());
    }

    @Order(2)
    @Test
    public void getPerson() {
        Person result = this.personDaoImpl.getRecordById(this.id);
        assertThat(this.id).isEqualTo(result.getId());
    }

    @Order(3)
    @Test
    public void count() {
        Query query = new Query();
        query.addCriteria(Criteria.where(Animal.FILTER_KIND).is(Person.class.getSimpleName()));
        long count = this.personDaoImpl.count(query, "item");
        assertThat(1).isEqualTo(count);
    }

    @Order(4)
    @Test
    public void delete() {
        Person result = this.personDaoImpl.deleteRecord(this.id);
        assertThat(result.getId()).isEqualTo(this.id);
        Query query = new Query();
        query.addCriteria(Criteria.where(Animal.FILTER_KIND).is(Person.class.getSimpleName()));
        long count = this.personDaoImpl.count(query, "item");
        assertThat(0).isEqualTo(count);
    }
}
