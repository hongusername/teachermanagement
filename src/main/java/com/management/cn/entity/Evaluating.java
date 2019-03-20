package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
* 评测结果表
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluating {
    //主键
    private Integer id;
    //老师id
    private Integer teacherid;
    //评测事件
    private Date date;
    //平均分数
    private Float socore;
}
