package com.ted.cockpit.desk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class DeskConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(createApiInfo()).select().build();
	}

	private ApiInfo createApiInfo() {
		final ApiInfo apiInfo = new ApiInfo("Reservation API", "This is the API for using the reservation tool",
			"1.0", "#/termsOfServiceUrl.html", "PRODYNA AG", "License", "#/licenseUrl.html");
		return apiInfo;
	}
}
