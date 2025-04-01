package com.example.schedulehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleHubApplication.class, args);
    }

}
