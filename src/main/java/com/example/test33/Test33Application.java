package com.example.test33;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.example.test33")
@EntityScan("com.example.test33.model")
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.example.test33.Repository")
public class Test33Application {

	public static void main(String[] args) {
		SpringApplication.run(Test33Application.class, args);
	}

}
