package com.gisp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EntityScan("com.gisp.domain")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.gisp.repository")
@OpenAPIDefinition(servers = {
       // @Server(url = "https://gisp-api.kovalev.team"),
        @Server(url = "http://localhost:8888")}
)
public class GispApplication {

    public static void main(String[] args) {
        SpringApplication.run(GispApplication.class, args);
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        factory.setMaxFileSize(DataSize.parse("20000KB"));
        factory.setMaxRequestSize(DataSize.parse("20000KB"));

        return factory.createMultipartConfig();
    }


    @Bean
    public Docket swaggerDemoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gisp"))
                .build();
    }
}
