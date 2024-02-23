package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Dto.PropertyDTO;
import com.example.realestatemanagment.Filter.JwtRequestFilter;
import com.example.realestatemanagment.Service.PropertyService;
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

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@WebMvcTest(PropertyControllerTest.class)
@AutoConfigureMockMvc(addFilters = false)
class PropertyControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtUtils jwtUtils;
    @MockBean
    JwtRequestFilter jwtRequestFilter;
    @MockBean
    PropertyService propertyService;

    @Test
    void ShouldReturnProperty() throws Exception{
        PropertyDTO dto = new PropertyDTO();

        dto.setId(123L);
        dto.setType("Appartement");
        dto.setAddress("Holendrecht20");
        dto.setCurrentPrice(17000.00);
        dto.setSquareFeet("150M2");
        dto.setBuildYear(1999);
        dto.setBoughtPrice(15000.00);
        dto.setRented(true);


        Mockito.when(propertyService.getPropertyById(anyLong())).thenReturn(dto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/properties/1234"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type",is("Appartement")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address",is("Holendrecht 20")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentPrice",is(17000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.squareFeet",is("150M2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.buildYear",is(1999)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.boughtPrice",is(15000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rented",is(12)));


    }


}