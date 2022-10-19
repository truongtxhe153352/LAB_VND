package edu.hanoi.jazz.springjazz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = {"edu.hanoi.jazz.*"})
@ImportResource("classpath:config.xml")
public class SpringJazzApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJazzApplication.class, args);
        ctx.start();
    }

}
