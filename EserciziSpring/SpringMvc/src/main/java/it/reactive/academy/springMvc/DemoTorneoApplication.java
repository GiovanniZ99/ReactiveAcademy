package it.reactive.academy.SpringMvcStep1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class DemoTorneoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTorneoApplication.class, args);
	}

}
