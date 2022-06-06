package com.example.what_course.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CourseServiceImplTest {
    @Autowired
    CourseServiceImpl courseService;

    @Test
    public void getCurrencyTest(){
        assertNotNull(courseService.getCurrency());
    }

    @Test
    public void getGifTest(){
        assertNotNull(courseService.getGif("rub"));
    }

    @Test
    public void getGifNullTest(){
        assertNull(courseService.getGif("r"));
    }

}
