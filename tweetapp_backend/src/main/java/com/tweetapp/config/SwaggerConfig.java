package com.tweetapp.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/v1.0/tweets/**"))
				.apis(RequestHandlerSelectors.basePackage("com.tweetapp"))
				.build()
				.apiInfo(apiDetails());
				
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				      "Pension Disbursement Service API Documentation",
				      "Pension Disbursement Service API for Swagger",
				      "1.0",
				      "Free to use",
				      new springfox.documentation.service.Contact("Arpit,Krishna","http://www.pensionbuddy.com","combine.pensionbuddy@gmail.com"),
				      "API Licence",
				      "http://www.pensionbuddy.com",
				      Collections.emptyList());
	}
	
}

