package com.endor.labs.dao;

import com.endor.labs.dao.impl.PersonDaoImpl;
import com.endor.labs.exceptions.MaximumRetryException;
import com.endor.labs.model.Animal;
import com.endor.labs.model.Person;
import com.endor.labs.utils.IdGenerator;
import com.endor.labs.utils.Preconditions;
import org.junit.Rule;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonDaoImplTest {

    @Autowired
    private PersonDaoImpl personDaoImpl;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Declaring the id here as I am testing all CRUD operations with only one record.
     * This can be change if we start testing with multiple records (We can avoid storing the id).
     */
    private static final String id = IdGenerator.getUuid();

    /**
     * Save
     */
    @Order(1)
    @Test
    public void savePerson() {
        Person person = new Person().setName("Endor").setId(this.id).setKind(Person.class.getSimpleName());
        Person result = this.personDaoImpl.save(person);
        assertThat(person.getName()).isEqualTo(result.getName());
    }

    /**
     This is a findById end-point to check whether the record id is present or not.
     */
    @Order(2)
    @Test
    public void getPerson() {
        Person result = this.personDaoImpl.getRecordById(this.id);
        assertThat(this.id).isEqualTo(result.getId());
    }

    /**
     * This is the method throws an exception as Query argument is null.
     */
    @Order(3)
    @Test
    public void checkPreconditionException() {
        assertThrows(Preconditions.PreconditionException.class, () -> {
            this.personDaoImpl.findOne(null);
        });
    }

    /**
     * This will tell you how many records are present under this KIND.
     */
    @Order(4)
    @Test
    public void count() {
        Query query = new Query();
        query.addCriteria(Criteria.where(Animal.FILTER_KIND).is(Person.class.getSimpleName()));
        long count = this.personDaoImpl.count(query, "item");
        assertThat(1).isEqualTo(count);
    }

    /**
     * This method deletes the record that we specified and assert the count of records. In this case we inserted one and deleted that one record.
     */
    @Order(5)
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
