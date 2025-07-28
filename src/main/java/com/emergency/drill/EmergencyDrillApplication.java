package com.emergency.drill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmergencyDrillApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmergencyDrillApplication.class, args);
    }
}