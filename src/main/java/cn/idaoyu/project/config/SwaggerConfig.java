package cn.idaoyu.project.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 一条秋刀鱼zz
 * @className SwaggerConfig
 * @description swagger 接口文档配置
 * @date 2022/12/11 12:15
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    private Boolean enable;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("daoyu-spring-boot-template")
                .description("daoyu-spring-boot-template 接口文档")
                .contact(new Contact("一条秋刀鱼zz"
                        , "https://github.com/daoyui/daoyu-spring-boot-template"
                        , "qchenzexuan@vip.qq.com"))
                .version("v0.1")
                .license("MIT License")
                .licenseUrl("https://mit-license.org/")
                .build();
    }
}
