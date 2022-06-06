package com.example.what_course.controller;


import com.example.what_course.model.dto.CurrencyDto;
import com.example.what_course.model.dto.GifDto;
import com.example.what_course.model.dto.ImageDto;
import com.example.what_course.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final CourseService courseService;

    public MainController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/course/{currency}")
    public ResponseEntity<ImageDto> getCourse(@PathVariable String currency){
        ImageDto imageDto = courseService.getGif(currency);
        if (imageDto != null)
             return new ResponseEntity<>(imageDto, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/currency")
    public ResponseEntity<CurrencyDto> getCurrency(){
        return new ResponseEntity<>(courseService.getCurrency(), HttpStatus.OK);
    }
}
