package edu.hanoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.*"})
//@ImportResource("classpath:config.xml")
public class SpringClassApplication {


	public static void main(String[] args) {
	SpringApplication.run(SpringClassApplication.class, args);

	}

}
