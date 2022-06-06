package com.example.what_course.rest;


import com.example.what_course.model.dto.GifDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${name.gif.api}", url = "${url.gif.api}")
public interface GifClient {
    @GetMapping
    GifDto getGif(@RequestParam("api_key") String apiKey,
                  @RequestParam String q,
                  @RequestParam int limit,
                  @RequestParam int offset);
}
