package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
* 结果表
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    //主键
    private Integer id;
    //所属班级
    private Integer classid;
    //分数
    private Integer score;
    //类型  1.教员 2.班主任
    private Integer type;


}
