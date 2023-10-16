package com.example.test33;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.test33.model")
public class Test33Application {

	public static void main(String[] args) {
		SpringApplication.run(Test33Application.class, args);
	}

}
