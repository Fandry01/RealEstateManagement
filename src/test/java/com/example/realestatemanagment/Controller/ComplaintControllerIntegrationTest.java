package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Models.Complaint;
import com.example.realestatemanagment.Repository.ComplaintRepository;
import org.junit.jupiter.api.BeforeEach;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ComplaintControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ComplaintRepository complaintRepo;

    Complaint complaint1;
    @BeforeEach
    public void setUp(){
        complaint1 = new Complaint(1L, LocalDate.of(2024,03,03),"no hot water");
        complaintRepo.save(complaint1);
    }

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
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("dateOfComplaint").value("2024-10-10"))
                .andExpect(jsonPath("complaintMessage").value("heaterbroken"))

                .andReturn();



        assertThat(result.getResponse().getHeader("Location"), matchesPattern("^.*/complaints/"+ 1));

    }

    @Test
    public void shouldReturnComplaintById() throws Exception {
        mockMvc.perform(get("/complaints/" + complaint1.getId().longValue())).
                andExpect(status().isOk())
                .andExpect(jsonPath("id").value(complaint1.getId().longValue()))
                .andExpect(jsonPath("dateOfComplaint").value("2024-03-03"))
                .andExpect(jsonPath("complaintMessage").value("no hot water"));
    }


}