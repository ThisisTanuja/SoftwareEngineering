package com.sports.goteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.sports.goteam.model")
public class GoteamApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoteamApplication.class, args);
	}

}
