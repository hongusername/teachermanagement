package com.management.cn.teachermanagementproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.management.cn.*.dao")
public class TeachermanagementprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeachermanagementprojectApplication.class, args);
    }

}
