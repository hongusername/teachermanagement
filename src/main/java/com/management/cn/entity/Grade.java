package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* 学期表
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    //主键
    private Integer id;
    //学期名
    private Integer semester;

}
