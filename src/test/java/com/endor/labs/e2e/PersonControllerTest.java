package com.endor.labs.e2e;

import com.endor.labs.controllers.PersonController;
import com.endor.labs.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @Autowired
    private MockMvc mockMvc;

    /**
     * We can replace this id generation with a random id generation. For testing purpose we are using this as a constant
     */
    private final String id = "123";

    /*
        This method save the record and assert the name that was inserted.
     */
    @Order(1)
    @Test
    public void save() throws Exception {
        Person person = new Person().setId(id).setName("EndorE2E").setKind(Person.class.getSimpleName());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/person/")
                        .content(asJsonString(person))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
        Sending an HTTP Request and assert the response. This is a findById end-point to check whether the record id is present or not.
     */
    @Order(2)
    @Test
    public void findById() throws Exception {
        this.mockMvc.perform(get("/person/{id}", this.id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(this.id));
    }

    @Order(3)
    @Test
    public void list() throws Exception {
        this.mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", containsInAnyOrder("123")))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("EndorE2E")));
    }

    @Order(4)
    @Test
    public void deleteRecord() throws Exception {
        this.mockMvc.perform(delete("/person/{id}", this.id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"));
    }
}
