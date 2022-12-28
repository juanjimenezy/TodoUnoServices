package com.todouno.app.configs;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final String TITTLE = "API todo1"; 
	private static final String TITTLE2 = "Spring Boot API para la gestión servicios";
	private static final String VERSION = "1.0";
	private static final String TERMINOS = "Terminos del Servicio";
	
	@Bean
	  public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("com.todouno.app"))              
	          .paths(PathSelectors.any())                          
	          .build()
	          .apiInfo(metaDato());                                           
	    }
	  
	  private ApiInfo metaDato() {
			return new ApiInfo(				
				TITTLE,
				TITTLE2, 
				VERSION, 
				TERMINOS, 
				new Contact("Keralty", "http://www.google.com.com", ""), 
				"TodoUnoServices", 
				"http://www.google.com",
				Collections.emptyList()
		);
	}

}
