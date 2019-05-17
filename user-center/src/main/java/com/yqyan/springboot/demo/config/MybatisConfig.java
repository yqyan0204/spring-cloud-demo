package com.yqyan.springboot.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yanyaqiang
 * @Date 2019/4/26 10:14
 **/

@Configuration
@MapperScan("com.yqyan.springboot.demo.dao")
public class MybatisConfig {
}
