package com.xzst.modi.app.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by li on 2017/7/4.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    //添加全局参数
    private List<Parameter> getTokenParam(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

    @Bean
    public Docket test() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("TestSwagger")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/swagger/.*"))//过滤的接口
                .build()
                .globalOperationParameters(getTokenParam())
                .apiInfo(detailInfo("TestSwagger"));
    }


    @Bean
    public Docket userManage() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("UserLogin")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/usermanage/.*"))//过滤的接口
                .build()
//                .globalOperationParameters(getTokenParam())
                .apiInfo(detailInfo("用户登录"));
    }


    @Bean
    public Docket templateInfoBean() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("templateInfo")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/model/.*"))//过滤的接口
                .build()
                .globalOperationParameters(getTokenParam())
                .apiInfo(detailInfo("模型结果集信息"));
    }


    @Bean
    public Docket p2cglBean() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("p2cgl")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/p2cgl/.*"))//过滤的接口
                .build()
                .globalOperationParameters(getTokenParam())
                .apiInfo(detailInfo("人车关联"));
    }


    @Bean
    public Docket kafka() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("kafka模拟发送数据")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/kafka/.*"))//过滤的接口
                .build()
//                .globalOperationParameters(getTokenParam())
                .apiInfo(detailInfo("kafka模拟发送数据"));
    }



    private ApiInfo detailInfo(String title) {
        return new ApiInfoBuilder()
                .title(title)//大标题
                .build();
    }
}
