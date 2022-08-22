package com.example.demo;

import org.springframework.context.annotation.*;

import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30).useDefaultResponseMessages(false).select().apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any()).build().pathMapping("/").apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("MProject11 API Document").description("MProject11 REST API 설명").version("1.0").build();
    }
}
