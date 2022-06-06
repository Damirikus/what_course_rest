package com.example.what_course.service;

import com.example.what_course.model.dto.CourseDto;
import com.example.what_course.model.dto.CurrencyDto;
import com.example.what_course.model.dto.GifDto;
import com.example.what_course.model.dto.ImageDto;

public interface CourseService {
    ImageDto getGif(String currency);
    CurrencyDto getCurrency();
}
