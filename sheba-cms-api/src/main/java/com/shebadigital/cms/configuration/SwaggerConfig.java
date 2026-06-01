/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shebadigital.cms.configuration;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	
	  private ApiKey apiKey() {
	        return new ApiKey("Authorization", "Authorization", "header");
	    }

	    SecurityReference securityReference = SecurityReference.builder()
	            .reference("Authorization")
	            .scopes(new AuthorizationScope[0])
	            .build();

	    SecurityContext securityContext = SecurityContext.builder()
	            .securityReferences(Arrays.asList(securityReference))
	            .build();


	    @Bean
	    public Docket productApi() {

	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.shebadigital.cms"))
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(metaData())
	                .securitySchemes(Arrays.asList(apiKey()))
	                .securityContexts(Arrays.asList(securityContext));
	    }


		private ApiInfo metaData() {
	        ApiInfo apiInfo = new ApiInfo(
	                "Sheba CMS API",
	                "A restfull api for Sheba CMS client",
	                "1.0",
	                "Terms of service",
	                new Contact("Sheba Digital Limited", "http://www.shebadigital.com/", "info@shebadigital.com"),
	                "Sheba Digital Limited",
	                "http://www.shebadigital.com/",new ArrayList<>());
	        return apiInfo;
	    }
    
}
