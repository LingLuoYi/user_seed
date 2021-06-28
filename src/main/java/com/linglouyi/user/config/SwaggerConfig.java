package com.linglouyi.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author linglouyi
 */
@Configuration
@EnableSwagger2
@Profile({"dev","local"})
public class SwaggerConfig {

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("优快修")
                .description("用户端")
                .version("2.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //这里写的是API接口所在的包位置
                .apis(RequestHandlerSelectors.basePackage("com.linglouyi.user.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
