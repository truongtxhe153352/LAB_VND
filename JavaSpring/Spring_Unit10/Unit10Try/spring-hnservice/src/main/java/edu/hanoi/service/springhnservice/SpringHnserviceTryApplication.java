package edu.hanoi.service.springhnservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"edu.hanoi.service.*"})
public class SpringHnserviceTryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHnserviceTryApplication.class, args);
	}

}
