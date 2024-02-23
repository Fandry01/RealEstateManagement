package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Dto.ComplaintDTO;
import com.example.realestatemanagment.Filter.JwtRequestFilter;
import com.example.realestatemanagment.Service.ComplaintService;
import com.example.realestatemanagment.Utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.hamcrest.Matchers.is;

import static org.mockito.ArgumentMatchers.anyLong;


@WebMvcTest(ComplaintController.class)
@AutoConfigureMockMvc(addFilters = false)
class ComplaintControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtUtils jwtUtils;
    @MockBean
    JwtRequestFilter requestFilter;
    @MockBean
    ComplaintService complaintService;

    @Test
    void shouldReturnComplaint() throws Exception{
        ComplaintDTO dto = new ComplaintDTO();
        dto.setComplaintMessage("heater broken");
        dto.setDateOfComplaint(new Date(2024-10-10));
        dto.setId(123L);

        Mockito.when(complaintService.getComplaintsById(anyLong())).thenReturn(dto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/complaints/123"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.complaintMessage",is("heater broken")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfComplaint",is("2024-10-10")));

    }


}