package com.joaoguilhermmy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info()
                .title("REST API's RESTful from 0 with java, spring boot, kurbenetes and docker").version("v1")
                .description("REST API's RESTful from 0 with java, spring boot, kurbenetes and docker")
                .license(new License().name("Apache 2.0")));

    }
}
