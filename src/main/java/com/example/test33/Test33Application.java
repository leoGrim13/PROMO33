package com.example.test33;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.example.test33.model")
@EnableScheduling
@EnableJpaRepositories
public class Test33Application {

	public static void main(String[] args) {
		SpringApplication.run(Test33Application.class, args);
	}

}
