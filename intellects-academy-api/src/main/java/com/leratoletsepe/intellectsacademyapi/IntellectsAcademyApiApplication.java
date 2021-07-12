package com.leratoletsepe.intellectsacademyapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Intellect Academy", description = "API Documentation and Description for the Intellect Academy REST API", version = "v1", license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")))
public class IntellectsAcademyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntellectsAcademyApiApplication.class, args);
	}
}