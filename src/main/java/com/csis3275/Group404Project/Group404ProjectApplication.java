package com.csis3275.Group404Project;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * This is the first and only file that runs to start the Springboot application.
 * Make sure h2 is running. You can access the first page at localhost:8080
 *
 *
 */
@SpringBootApplication
public class Group404ProjectApplication {
	/**
	 * This is the main method that runs the Springboot application.
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(Group404ProjectApplication.class, args);
	}

}