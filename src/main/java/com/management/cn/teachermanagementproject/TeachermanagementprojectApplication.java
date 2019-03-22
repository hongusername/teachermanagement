package com.management.cn.teachermanagementproject;

import com.management.cn.entity.Teacher;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.management.cn.*.dao")
@ComponentScan(basePackages = {"com.management.cn.*"} )
public class TeachermanagementprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeachermanagementprojectApplication.class, args);
    }

}
