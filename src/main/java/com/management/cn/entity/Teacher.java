package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/*
*
* 教师表
*
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    //教师id
    private Integer teacherid;
    //教师名字
    private String name;
    //类型   (1.教员  2.班主任  3.教务)
    private Integer type;
    //(密码)
    private String pwd;
}
