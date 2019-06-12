package com.yqyan.api.gateway;

import com.yqyan.api.gateway.filters.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    //若启用，访问url必须带accessToken参数
//    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }

}
