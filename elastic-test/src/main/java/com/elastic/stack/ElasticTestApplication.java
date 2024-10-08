package com.elastic.stack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElasticTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticTestApplication.class, args);
    }

}
