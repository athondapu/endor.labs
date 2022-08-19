package com.endor.labs.e2e;

import com.endor.labs.controllers.AnimalController;
import com.endor.labs.controllers.PersonController;
import com.endor.labs.model.Animal;
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
public class AnimalControllerTest {

    @Autowired
    private AnimalController animalController;

    @Autowired
    private MockMvc mockMvc;

    /**
     * We can replace this id generation with a random id generation. For testing purpose we are using this as a constant
     */
    private final String id = "456";

    /*
        This method save the record and assert the name that was inserted.
     */
    @Order(1)
    @Test
    public void save() throws Exception {
        Animal animal = new Animal().setId(id).setName("Lion").setKind(Animal.class.getSimpleName());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/animal/")
                        .content(asJsonString(animal))
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
        this.mockMvc.perform(get("/animal/{id}", this.id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(this.id));
    }

    @Order(3)
    @Test
    public void list() throws Exception {
        this.mockMvc.perform(get("/animal/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(this.id)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Lion")));
    }

    @Order(4)
    @Test
    public void deleteRecord() throws Exception {
        this.mockMvc.perform(delete("/animal/{id}", this.id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(this.id));
    }
}
