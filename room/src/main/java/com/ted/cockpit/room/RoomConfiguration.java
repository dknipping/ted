package com.ted.cockpit.room;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableAutoConfiguration
@EnableSwagger2
@ComponentScan
public class RoomConfiguration {

    @Bean
    public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(createApiInfo())
				.select().build();
	}

	private ApiInfo createApiInfo() {
		final ApiInfo apiInfo = new ApiInfo("Room API",
				"This is the API for communicating with rooms", "1.0",
				"#/termsOfServiceUrl.html", "PRODYNA AG", "License",
				"#/licenseUrl.html");
		return apiInfo;
	}


}
