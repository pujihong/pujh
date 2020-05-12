package com.hewei.pujh.config;

import com.hewei.pujh.base.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2 接口配置  注意swagger2的版本，有些版本会有问题
 * 在生产环境禁用方法
 * 禁用方法1：使用注解@Profile({"dev","test"}) 表示在开发或测试环境开启，而在生产关闭。（推荐使用）
 * 禁用方法2：使用注解@ConditionalOnProperty(name = "swagger.enable", havingValue = "true") 然后在测试配置或者开发配置中 添加 swagger.enable = true 即可开启，生产环境不填则默认关闭Swagger.
 */

@Configuration
@EnableSwagger2
@Profile({"dev","test"}) // 若配置pro 会弹出alert 生产环境不给看
public class Swagger2Config {
    /*
      api接口包扫描路径
      这个不能适用通配符 比如 com.hewei.hewei.*.controller
      解决
      1、多个路径可使用公共拥有路径
      2、 .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
      */
    private static final String SWAGGER_SCAN_BASE_PACKAGE = "com.hewei.hewei";
    private static final String VERSION = "1.0";

    @Bean
    public Docket createRestApi() {
        /* 统一添加token */
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name(Constant.AUTHORIZATION).description("token").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API接口文档")
                .description("API接口文档")
                .contact(new Contact("pujihong", null, null))
                .version(VERSION)
                .build();
    }
}
