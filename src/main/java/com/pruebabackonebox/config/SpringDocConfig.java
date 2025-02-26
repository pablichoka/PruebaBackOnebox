package com.pruebabackonebox.config;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToExclude(
                        "/profile/**",
                        "/cart-entity/**",
                        "/product-entity/**",
                        "/product-search/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {

        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact contact = new Contact();
        contact.setName("Pablo Ruiz Abellán");
        contact.setEmail("pablikoruab98@gmail.com");

        return new OpenAPI()
                .info(new Info()
                        .title("PruebaBackOneBox API")
                        .version("1.0")
                        .contact(contact)
                        .description("API documentation for the application"))
                .servers(List.of(server));

    }
}