package com.example.realestatemanagment.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ComplaintControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void ShouldCreateComplaint() throws Exception{
        String requestJson = """
                {
                "dateOfComplaint": "2024-10-10",
                "complaintMessage": "heaterbroken"
                }
                """;

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/complaints")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("dateOfComplaint").value("2024-10-10"))
                .andExpect(jsonPath("complaintMessage").value("heaterbroken"))

                .andReturn();



        assertThat(result.getResponse().getHeader("Location"), matchesPattern("^.*/complaints/"+ 1));

    }

}