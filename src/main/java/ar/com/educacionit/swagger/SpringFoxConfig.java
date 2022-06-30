package ar.com.educacionit.swagger;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

	@Bean
	public Docket createRestApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))//solo las RestController
				.paths(PathSelectors.any())
				.build()
				.securityContexts(securityContext())
				.securitySchemes(securityScheme());
		return docket;
	}

	private List<SecurityScheme> securityScheme() {
		return Collections.singletonList(new ApiKey("JWT", "Authorization", "header"));
	}

	private List<SecurityContext> securityContext() {
		SecurityContext securityContext = SecurityContext.builder()
				.securityReferences(defaultAuth())
				.build();
				
		return Collections.singletonList(securityContext);
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authScope = new AuthorizationScope("global", "accessEverything");
		
//		AuthorizationScope[] authScopes = new AuthorizationScope[1];
//		authScopes[0] = authScope;
		
		//util para busquedas o creaciones de Colecciones
		return Collections.singletonList(new SecurityReference("JWT", new AuthorizationScope[] {authScope}));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.contact(new Contact("Jhon Cevedo", "Protalento", "soporte@protalento.com"))
			.description("Proyecto Rest final de bootcamp Protalento")
			.title("SpringBoot + Security + JWT + Swagger")
			.build();
	}
}
