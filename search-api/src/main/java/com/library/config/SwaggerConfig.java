package com.library.config;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI librarySearchAPI() {
        return new OpenAPI()
            .components(new Components())
            .info(new Info().title("spring-cloud-function-webmvc OpenAPI Demo").version("v0.0.1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}