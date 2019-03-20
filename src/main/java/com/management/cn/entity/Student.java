package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
* 学生表
*
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    //主键
    private Integer stu_id;
    //学生名字
    private String stu_name;
    //学生手机号
    private String stu_phone;
    //学生所属班级
    private Integer stu_class;
}
