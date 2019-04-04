package com.accenture.coap.master.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
	@Bean
	public Docket config() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).useDefaultResponseMessages(false).select()
				.apis(RequestHandlerSelectors.basePackage("com.accenture.coap.master.controller")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("COAP Admin Restful API Document")
				.contact(new Contact("Chen,Jianglei", "http://localhost/", "jianglei.chen@accenture.com")).build();
	}
}
