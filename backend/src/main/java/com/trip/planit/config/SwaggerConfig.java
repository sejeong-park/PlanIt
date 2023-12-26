package com.trip.planit.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
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
	
	private final ServerProperties serverProperties;

    public SwaggerConfig(ServerProperties serverProperties) {
		super();
		this.serverProperties = serverProperties;
	}

	/**
	 * version ?, title ?
	 * version 및 title: API의 버전 및 제목을 정의하는 문자열 변수입니다.
	 * http://localhost/swagger-ui/index.html#
	 * 
	 */
	private String version = "V1";
	private String title = "plan API : " + version;
	
	@Bean
	public Docket allApi() {
		return new Docket(DocumentationType.OAS_30)
				.consumes(getConsumeContentTypes())
				.produces(getProduceContentTypes())
				.groupName("ALL-API")
				.apiInfo(allApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.trip.planit"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.useDefaultResponseMessages(false);
	}

//	@Bean
//	public Docket userApi() {
//		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes())
//				.produces(getProduceContentTypes())
//				.apiInfo(apiInfo()).groupName(version).select()
//				.apis(RequestHandlerSelectors.basePackage("com.trip.planit.user.controller"))
//				.paths(regex("/users/.*")).build()
//				.useDefaultResponseMessages(false);
//	}
//	
//	@Bean
//	public Docket boardApi() {
//		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes())
//				.produces(getProduceContentTypes())
//				.apiInfo(apiInfo()).groupName(version).select()
//				.apis(RequestHandlerSelectors.basePackage("com.trip.planit.board.controller"))
//				.paths(regex("/users/.*")).build()
//				.useDefaultResponseMessages(false);
//	}
//	
//	@Bean
//	public Docket planApi() {
//		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes())
//				.produces(getProduceContentTypes())
//				.apiInfo(apiInfo()).groupName(version).select()
//				.apis(RequestHandlerSelectors.basePackage("com.trip.planit.plan.controller"))
//				.paths(regex("/plans/.*")).build()
//				.useDefaultResponseMessages(false);
//	}

	private Set<String> getConsumeContentTypes() {
		Set<String> consumes = new HashSet<>();
		consumes.add("application/json;charset=UTF-8");
		consumes.add("application/x-www-form-urlencoded");
		return consumes;
	}

	private Set<String> getProduceContentTypes() {
		Set<String> produces = new HashSet<>();
		produces.add("application/json;charset=UTF-8");
		return produces;
	}

	   private ApiInfo allApiInfo() {
	        Integer port = serverProperties.getPort();
	        return new ApiInfoBuilder()
	            .title("planit Swagger Open API Docs")
	            .description(
	                "<h3>Planit API Reference for Developers</h3>")
	            .version("1.0")
	            .license("Planit License")
	            .build();
	    }
	
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title(title)
//				.description(
//						"<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 Board API<br><img src=\"/assets/img/ssafy_logo.png\" width=\"150\">")
//				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
//				.license("SSAFY License")
//				.licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
//				.version("1.0").build();
//	}
}
