package com.example.what_course.rest;


import com.example.what_course.model.dto.CourseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${name.course.api}", url = "${url.course.api}")
public interface CourseClient {

    @GetMapping("latest.json")
    CourseDto getCourse(@RequestParam("app_id") String id);

    @GetMapping("historical/{date}")
    CourseDto getHistCourse(@RequestParam("app_id") String id, @PathVariable String date);
}
