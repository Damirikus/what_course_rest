package com.example.what_course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.example.what_course")
@SpringBootApplication
public class WhatCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatCourseApplication.class, args);
	}

}
