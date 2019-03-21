package com.management.cn.teachermanagementproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.management.cn.*.dao")

@ComponentScan(basePackages = {"com.management.cn.*"})

@ComponentScan(basePackages = {"com.management.cn.*"})  //指定扫描包路径

public class TeachermanagementprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeachermanagementprojectApplication.class, args);
    }

}
