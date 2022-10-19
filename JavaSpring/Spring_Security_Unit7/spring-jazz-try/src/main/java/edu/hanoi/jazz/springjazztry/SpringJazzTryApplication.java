package edu.hanoi.jazz.springjazztry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {"edu.hanoi.jazz.*"})
@ImportResource("classpath:config.xml")
public class SpringJazzTryApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringJazzTryApplication.class, args);
		ctx.start();
	}

}
