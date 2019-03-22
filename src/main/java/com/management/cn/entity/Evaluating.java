package com.management.cn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    //评测时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    //平均分数
    private Float score;

    private Teacher teacher;
}
