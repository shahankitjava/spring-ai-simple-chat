package org.ankit.demo.simplechat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SpringRagDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRagDemoApplication.class, args);
	}

}
