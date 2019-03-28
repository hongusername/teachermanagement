package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 评测表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluating {

    /**编号*/
    private Integer id;
    /**开始时间*/
    private String startTime;
    /**结束时间*/
    private String endTime;
    /**老师类型*/
    private Integer teacherType;
    /**年级*/
    private Grade grade;
    /**描述*/
    private String describe;
    /**参加本次测评的班级*/
    private List<String> classNameList;
}
