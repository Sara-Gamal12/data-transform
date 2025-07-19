package com.example.datatransform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties

public class DataTransformApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataTransformApplication.class, args);
    }

}
