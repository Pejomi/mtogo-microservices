package dk.pejomi.restaurantservice.controller;

import dk.pejomi.restaurantservice.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class RestaurantControllerIT {

    private final MockMvc mockMvc;


    @Autowired
    public RestaurantControllerIT(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


    @Test
    void getRestaurantById() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/restaurant/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }



    @Test
    void getRestaurantByZipCode() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/restaurant/zip/2500"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].zipCode").value("2500"));
    }
}