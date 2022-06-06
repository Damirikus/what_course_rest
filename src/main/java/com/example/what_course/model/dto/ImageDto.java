package com.example.what_course.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private int frames;
    private String hash;
    private int height;
    private int size;
    private String url;
    private int width;
    private String title;
    private String today;
    private String yesterday;
}
