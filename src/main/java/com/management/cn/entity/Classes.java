package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
* 班级
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classes {
    //主键
    private Integer class_id;
    //班级名称
    private String class_name;

    private Integer class_type; //班级类型
    //教员
    private Integer class_jy;
    //班主任
    private Integer class_bzr;

    //教师
    private Teacher teacher1;
    //教师
    private Teacher teacher2;
    //班级
    private Grade grade;

}
