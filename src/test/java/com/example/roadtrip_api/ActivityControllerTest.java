package com.example.roadtrip_api;

import com.example.roadtrip_api.activity.ActivityController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void createActivityTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/activity/new")
                        .content("{\n" +
                                "    \"name\": \"Parisian Food Walking Tour\",\n" +
                                "    \"description\": \"Sample the finest French delicacies while exploring charming neighborhoods, including cheese, wine, and pastry tastings.\",\n" +
                                "    \"city\": \"Paris\",\n" +
                                "    \"duration\": 180,\n" +
                                "    \"price\": 95,\n" +
                                "    \"category\": \"food\",\n" +
                                "    \"rating\": 4.7\n" +
                                "}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void emptyActivitiesTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/activities")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void getSingularActivityTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/activity/new")
                .content("{\n" +
                        "    \"name\": \"Parisian Food Walking Tour\",\n" +
                        "    \"description\": \"Sample the finest French delicacies while exploring charming neighborhoods, including cheese, wine, and pastry tastings.\",\n" +
                        "    \"city\": \"Paris\",\n" +
                        "    \"duration\": 180,\n" +
                        "    \"price\": 95,\n" +
                        "    \"category\": \"food\",\n" +
                        "    \"rating\": 4.7\n" +
                        "}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/activity/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllActivityTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/activity/new")
                .content("{\n" +
                        "    \"name\": \"Parisian Food Walking Tour\",\n" +
                        "    \"description\": \"Sample the finest French delicacies while exploring charming neighborhoods, including cheese, wine, and pastry tastings.\",\n" +
                        "    \"city\": \"Paris\",\n" +
                        "    \"duration\": 180,\n" +
                        "    \"price\": 95,\n" +
                        "    \"category\": \"food\",\n" +
                        "    \"rating\": 4.7\n" +
                        "}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mvc.perform(MockMvcRequestBuilders
                .post("/api/activity/new")
                .content("{\n" +
                        "    \"name\": \"Mount Fuji Sunrise Hike\",\n" +
                        "    \"description\": \"Experience the breathtaking sunrise from Japan's highest peak with traditional Japanese breakfast included.\",\n" +
                        "    \"city\": \"Tokyo\",\n" +
                        "    \"duration\": 720,\n" +
                        "    \"price\": 150,\n" +
                        "    \"category\": \"adventure\",\n" +
                        "    \"rating\": 4.9\n" +
                        "}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/activities")
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }
}
