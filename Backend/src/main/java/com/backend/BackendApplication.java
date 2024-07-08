package com.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BackendApplication {
  private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

  public static void main(String[] args) {
    log.info("online-quiz-game Application is started successfully.");
    SpringApplication.run(BackendApplication.class, args);
  }
}
