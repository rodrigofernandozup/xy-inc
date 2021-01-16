package com.xyinc.poi.config

import io.swagger.annotations.Api
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Api
@EnableSwagger2
@Configuration
class SwaggerConfig {

    @Bean
    open fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.xyinc.poi"))
        .paths(PathSelectors.any())
        .build();

    private fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder()
            .title("POI")
            .build()
    }
}