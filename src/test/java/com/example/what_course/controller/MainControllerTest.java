package com.example.what_course.controller;

import com.example.what_course.model.dto.CurrencyDto;
import com.example.what_course.model.dto.ImageDto;
import com.example.what_course.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MainController.class)
public class MainControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CourseService courseService;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void getCurrencyTest() throws Exception {
        CurrencyDto expectDto = new CurrencyDto();
        Mockito.when(courseService.getCurrency()).thenReturn(expectDto);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/currency")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        CurrencyDto resultDto = mapper.readValue(mvcResult.getResponse().getContentAsString(), CurrencyDto.class);
        assertEquals(expectDto, resultDto);
    }


    @Test
    public void getCourseTest() throws Exception {
        ImageDto expectDto = new ImageDto();
        Mockito.when(courseService.getGif("rub")).thenReturn(expectDto);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/course/{currency}", "rub")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ImageDto resultDto = mapper.readValue(mvcResult.getResponse().getContentAsString(), ImageDto.class);
        assertEquals(expectDto, resultDto);
    }


    @Test
    public void getCourseFailedTest() throws Exception {
        Mockito.when(courseService.getGif("r")).thenReturn(null);

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/course/{currency}", "r")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
