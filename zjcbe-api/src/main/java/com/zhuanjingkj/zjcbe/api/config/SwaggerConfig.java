package com.zhuanjingkj.zjcbe.api.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket vehicleAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("swagger接口配置").select()
                .apis(RequestHandlerSelectors.basePackage("com.yefeng.vehicle.api.controller"))
                .paths(PathSelectors.ant("/vehicle/api/**"))
                .build()
                .apiInfo(apiInfo());
    }

    @SuppressWarnings("deprecation")
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "接口服务手册",
                "接口返回值简易说明：" +
                        "返回数据格式统一为application/json,success==true && code=1表示成功；" +
                        "其他表示失败，失败原因参考message,data为接口返回数据。" +
                        "调用接口前请联系系统管理员获取授权appId或secretKey；" +
                        "调用登陆接口之后，获取授权accessToken，每次调用其他接口，将accessToken附加至Http请求头。",
                "1.0.0",
                "Terms of com.yefeng.vehicle.business.service",
                "pototallxg@qq.com",
                "刘小刚",
                "http://www.pototallxg.com/");
        return apiInfo;
    }
}
