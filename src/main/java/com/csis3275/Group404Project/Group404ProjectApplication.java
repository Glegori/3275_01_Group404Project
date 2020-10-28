package com.csis3275.Group404Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class Group404ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Group404ProjectApplication.class, args);
	}

}
