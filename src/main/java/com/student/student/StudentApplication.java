package com.student.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.student.student.entity")
public class StudentApplication {

	public static void main(String[] args) {

		SpringApplication.run(StudentApplication.class, args);
		
	}

}
