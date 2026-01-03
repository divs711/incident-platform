package com.example.ip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IncidentPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentPlatformApplication.class, args);
	}

}
