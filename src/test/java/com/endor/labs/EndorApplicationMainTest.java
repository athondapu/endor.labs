package com.endor.labs;

import com.endor.labs.controllers.PersonController;
import com.endor.labs.model.Person;
import com.endor.labs.utils.IdGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * THis is another approach to test the end-points without starting the server at all. Here we are mocking the HTTP requests.
 * Spring provides a way to mock the requests and hand it over to the controller. The whole process is exactly the same way that we are processing the real HTTP request.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class EndorApplicationMainTest {

    @Autowired
    private PersonController personController;

    @Autowired
    private MockMvc mockMvc;

    /*
        Sending an HTTP Request and assert the response. This is a default end-point to check application started or not.
     */
    @Test
    public void ping() throws Exception {
        this.mockMvc.perform(get("/ping")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("PONG")));
    }
}
