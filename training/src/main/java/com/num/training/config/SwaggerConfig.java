package com.num.training.config;

/**
 * @description: swagger配置
 * package: com.nmu.com.num.training.config
 * className: SwaggerConfig
 * @author: luran5
 * @date: 2022/7/20 15:07
 * @since V1.0
 */



import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;

import springfox.documentation.service.Contact;

import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的接口配置
 *
 * @author ruoyi
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 是否启用Swagger
                .enable(true)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                .select()
                //RequestHandlerSelectors:配置要扫描接口的方式
                //basePackage:指定要扫描的包
                //any:扫描全部
                //none：不扫描
                //withClassAnnotation：扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation：扫描方法上的注解
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //paths:过滤什么路径
                //.paths(PathSelectors.ant("/com/sise/pass/**"))
                .build();

                // 设置哪些接口暴露给Swagger展示
                //.select()
                // 扫描所有有注解的api，用这种方式更灵活
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                // .apis(RequestHandlerSelectors.basePackage("com.ruoyi.project.tool.swagger"))
                //.apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.any())
                //.build();
                /* 设置安全模式，swagger可以设置访问token */
                //.securitySchemes(securitySchemes())
                //.securityContexts(securityContexts())
                //.pathMapping(pathMapping);
    }
    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo()
    {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("标题：北方民族大学食品安全抽样系统_接口文档")
                // 描述
                .description("描述：用于北方民族大学食品安全抽样系统,具体包括验证码,登录模块")
                // 作者信息
                .contact(new Contact("lurran", null, null))
                // 版本
                .version("版本号:" + "1.0.0")
                .build();
    }
}
