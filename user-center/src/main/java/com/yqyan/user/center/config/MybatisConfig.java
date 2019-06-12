package com.yqyan.user.center.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yanyaqiang
 * @Date 2019/4/26 10:14
 **/

@Configuration
@MapperScan("com.yqyan.user.center.dao")
public class MybatisConfig {
}
