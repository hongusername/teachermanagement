package com.management.cn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: GengHao
 * @date: 2019-03-25 16:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    /**
     * 教师id
     */
    private Integer teacherid;
    /**
     * 教师名字
     */
    private String name;
    /**
     * 类型   (1.教员  2.班主任  3.教务)
     */
    private Integer type;
    /**
     * 平均分
     */
    private Integer avg;

    /**
     * 评测时间
     */
    private Date date;
}
