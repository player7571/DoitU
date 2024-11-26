package com.example.DoitU;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DoitUApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoitUApplication.class, args);
	}

}
