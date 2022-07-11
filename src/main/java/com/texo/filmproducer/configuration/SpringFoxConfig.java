package com.texo.filmproducer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Value("${spring.application.name}")
    private String application;

    @Value("${springfox.documentation.swagger.api-info.version:1.0}")
    private String version;

    @Bean
    public Docket api() {
        final var apiInfo = (new ApiInfoBuilder())
                .title(this.application)
                .description("Api Documentation")
                .version(this.version).build();

        return (new Docket(DocumentationType.SWAGGER_2))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.texo"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo);
    }
}
