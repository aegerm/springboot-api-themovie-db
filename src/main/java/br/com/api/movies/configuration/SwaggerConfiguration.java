package br.com.api.movies.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * api
     *
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.api.movies.resources"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(apiKey()));
    }

    /**
     * apiKey
     *
     * @return
     */
    private ApiKey apiKey() {
        return new ApiKey("jwtToken", "Authorization", "header");
    }

    /**
     * apiInfo
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API Credits TheMovieDb",
                "Está API é utilizada para listar créditos, temporadas e demais informações de uma série. Based in https://developers.themoviedb.org/3/credits/get-credit-details",
                "Versão 1.0",
                "https://github.com/aegerm/",
                new Contact("Alexandre Eger Marques", "https://github.com/aegerm/", "alexandreegermarques@gmail.com"),
                "",
                "https://www.themoviedb.org/documentation/api/terms-of-use",
                Collections.emptyList()
        );
    }
}
