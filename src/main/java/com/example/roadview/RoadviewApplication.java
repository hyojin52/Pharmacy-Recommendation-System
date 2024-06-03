package com.example.roadview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RoadviewApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(RoadviewApplication.class, args);
  }
  
}
